package com.siga.constat.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.siga.constat.IntegrationTest;
import com.siga.constat.domain.Entry;
import com.siga.constat.repository.EntryRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link EntryResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class EntryResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/entries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EntryRepository entryRepository;

    @Mock
    private EntryRepository entryRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntryMockMvc;

    private Entry entry;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entry createEntity(EntityManager em) {
        Entry entry = new Entry().title(DEFAULT_TITLE).content(DEFAULT_CONTENT).date(DEFAULT_DATE);
        return entry;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entry createUpdatedEntity(EntityManager em) {
        Entry entry = new Entry().title(UPDATED_TITLE).content(UPDATED_CONTENT).date(UPDATED_DATE);
        return entry;
    }

    @BeforeEach
    public void initTest() {
        entry = createEntity(em);
    }

    @Test
    @Transactional
    void createEntry() throws Exception {
        int databaseSizeBeforeCreate = entryRepository.findAll().size();
        // Create the Entry
        restEntryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(entry)))
            .andExpect(status().isCreated());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeCreate + 1);
        Entry testEntry = entryList.get(entryList.size() - 1);
        assertThat(testEntry.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testEntry.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testEntry.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    void createEntryWithExistingId() throws Exception {
        // Create the Entry with an existing ID
        entry.setId(1L);

        int databaseSizeBeforeCreate = entryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(entry)))
            .andExpect(status().isBadRequest());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = entryRepository.findAll().size();
        // set the field null
        entry.setTitle(null);

        // Create the Entry, which fails.

        restEntryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(entry)))
            .andExpect(status().isBadRequest());

        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = entryRepository.findAll().size();
        // set the field null
        entry.setDate(null);

        // Create the Entry, which fails.

        restEntryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(entry)))
            .andExpect(status().isBadRequest());

        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllEntries() throws Exception {
        // Initialize the database
        entryRepository.saveAndFlush(entry);

        // Get all the entryList
        restEntryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entry.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntriesWithEagerRelationshipsIsEnabled() throws Exception {
        when(entryRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntryMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(entryRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntriesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(entryRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntryMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(entryRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getEntry() throws Exception {
        // Initialize the database
        entryRepository.saveAndFlush(entry);

        // Get the entry
        restEntryMockMvc
            .perform(get(ENTITY_API_URL_ID, entry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entry.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingEntry() throws Exception {
        // Get the entry
        restEntryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEntry() throws Exception {
        // Initialize the database
        entryRepository.saveAndFlush(entry);

        int databaseSizeBeforeUpdate = entryRepository.findAll().size();

        // Update the entry
        Entry updatedEntry = entryRepository.findById(entry.getId()).get();
        // Disconnect from session so that the updates on updatedEntry are not directly saved in db
        em.detach(updatedEntry);
        updatedEntry.title(UPDATED_TITLE).content(UPDATED_CONTENT).date(UPDATED_DATE);

        restEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntry.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEntry))
            )
            .andExpect(status().isOk());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeUpdate);
        Entry testEntry = entryList.get(entryList.size() - 1);
        assertThat(testEntry.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testEntry.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testEntry.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingEntry() throws Exception {
        int databaseSizeBeforeUpdate = entryRepository.findAll().size();
        entry.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entry.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entry))
            )
            .andExpect(status().isBadRequest());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntry() throws Exception {
        int databaseSizeBeforeUpdate = entryRepository.findAll().size();
        entry.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entry))
            )
            .andExpect(status().isBadRequest());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntry() throws Exception {
        int databaseSizeBeforeUpdate = entryRepository.findAll().size();
        entry.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(entry)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntryWithPatch() throws Exception {
        // Initialize the database
        entryRepository.saveAndFlush(entry);

        int databaseSizeBeforeUpdate = entryRepository.findAll().size();

        // Update the entry using partial update
        Entry partialUpdatedEntry = new Entry();
        partialUpdatedEntry.setId(entry.getId());

        partialUpdatedEntry.title(UPDATED_TITLE).content(UPDATED_CONTENT).date(UPDATED_DATE);

        restEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntry.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntry))
            )
            .andExpect(status().isOk());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeUpdate);
        Entry testEntry = entryList.get(entryList.size() - 1);
        assertThat(testEntry.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testEntry.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testEntry.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateEntryWithPatch() throws Exception {
        // Initialize the database
        entryRepository.saveAndFlush(entry);

        int databaseSizeBeforeUpdate = entryRepository.findAll().size();

        // Update the entry using partial update
        Entry partialUpdatedEntry = new Entry();
        partialUpdatedEntry.setId(entry.getId());

        partialUpdatedEntry.title(UPDATED_TITLE).content(UPDATED_CONTENT).date(UPDATED_DATE);

        restEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntry.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntry))
            )
            .andExpect(status().isOk());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeUpdate);
        Entry testEntry = entryList.get(entryList.size() - 1);
        assertThat(testEntry.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testEntry.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testEntry.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingEntry() throws Exception {
        int databaseSizeBeforeUpdate = entryRepository.findAll().size();
        entry.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entry.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(entry))
            )
            .andExpect(status().isBadRequest());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntry() throws Exception {
        int databaseSizeBeforeUpdate = entryRepository.findAll().size();
        entry.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(entry))
            )
            .andExpect(status().isBadRequest());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntry() throws Exception {
        int databaseSizeBeforeUpdate = entryRepository.findAll().size();
        entry.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(entry)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Entry in the database
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntry() throws Exception {
        // Initialize the database
        entryRepository.saveAndFlush(entry);

        int databaseSizeBeforeDelete = entryRepository.findAll().size();

        // Delete the entry
        restEntryMockMvc
            .perform(delete(ENTITY_API_URL_ID, entry.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Entry> entryList = entryRepository.findAll();
        assertThat(entryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
