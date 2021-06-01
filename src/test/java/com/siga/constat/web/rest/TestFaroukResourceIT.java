package com.siga.constat.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.siga.constat.IntegrationTest;
import com.siga.constat.domain.TestFarouk;
import com.siga.constat.repository.TestFaroukRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TestFaroukResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TestFaroukResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_HISTORY = "AAAAAAAAAA";
    private static final String UPDATED_HISTORY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/test-farouks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TestFaroukRepository testFaroukRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTestFaroukMockMvc;

    private TestFarouk testFarouk;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TestFarouk createEntity(EntityManager em) {
        TestFarouk testFarouk = new TestFarouk().nom(DEFAULT_NOM).adresse(DEFAULT_ADRESSE).history(DEFAULT_HISTORY);
        return testFarouk;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TestFarouk createUpdatedEntity(EntityManager em) {
        TestFarouk testFarouk = new TestFarouk().nom(UPDATED_NOM).adresse(UPDATED_ADRESSE).history(UPDATED_HISTORY);
        return testFarouk;
    }

    @BeforeEach
    public void initTest() {
        testFarouk = createEntity(em);
    }

    @Test
    @Transactional
    void createTestFarouk() throws Exception {
        int databaseSizeBeforeCreate = testFaroukRepository.findAll().size();
        // Create the TestFarouk
        restTestFaroukMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(testFarouk)))
            .andExpect(status().isCreated());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeCreate + 1);
        TestFarouk testTestFarouk = testFaroukList.get(testFaroukList.size() - 1);
        assertThat(testTestFarouk.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTestFarouk.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testTestFarouk.getHistory()).isEqualTo(DEFAULT_HISTORY);
    }

    @Test
    @Transactional
    void createTestFaroukWithExistingId() throws Exception {
        // Create the TestFarouk with an existing ID
        testFarouk.setId(1L);

        int databaseSizeBeforeCreate = testFaroukRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTestFaroukMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(testFarouk)))
            .andExpect(status().isBadRequest());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTestFarouks() throws Exception {
        // Initialize the database
        testFaroukRepository.saveAndFlush(testFarouk);

        // Get all the testFaroukList
        restTestFaroukMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(testFarouk.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].history").value(hasItem(DEFAULT_HISTORY)));
    }

    @Test
    @Transactional
    void getTestFarouk() throws Exception {
        // Initialize the database
        testFaroukRepository.saveAndFlush(testFarouk);

        // Get the testFarouk
        restTestFaroukMockMvc
            .perform(get(ENTITY_API_URL_ID, testFarouk.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(testFarouk.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.history").value(DEFAULT_HISTORY));
    }

    @Test
    @Transactional
    void getNonExistingTestFarouk() throws Exception {
        // Get the testFarouk
        restTestFaroukMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTestFarouk() throws Exception {
        // Initialize the database
        testFaroukRepository.saveAndFlush(testFarouk);

        int databaseSizeBeforeUpdate = testFaroukRepository.findAll().size();

        // Update the testFarouk
        TestFarouk updatedTestFarouk = testFaroukRepository.findById(testFarouk.getId()).get();
        // Disconnect from session so that the updates on updatedTestFarouk are not directly saved in db
        em.detach(updatedTestFarouk);
        updatedTestFarouk.nom(UPDATED_NOM).adresse(UPDATED_ADRESSE).history(UPDATED_HISTORY);

        restTestFaroukMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTestFarouk.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTestFarouk))
            )
            .andExpect(status().isOk());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeUpdate);
        TestFarouk testTestFarouk = testFaroukList.get(testFaroukList.size() - 1);
        assertThat(testTestFarouk.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTestFarouk.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testTestFarouk.getHistory()).isEqualTo(UPDATED_HISTORY);
    }

    @Test
    @Transactional
    void putNonExistingTestFarouk() throws Exception {
        int databaseSizeBeforeUpdate = testFaroukRepository.findAll().size();
        testFarouk.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTestFaroukMockMvc
            .perform(
                put(ENTITY_API_URL_ID, testFarouk.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(testFarouk))
            )
            .andExpect(status().isBadRequest());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTestFarouk() throws Exception {
        int databaseSizeBeforeUpdate = testFaroukRepository.findAll().size();
        testFarouk.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTestFaroukMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(testFarouk))
            )
            .andExpect(status().isBadRequest());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTestFarouk() throws Exception {
        int databaseSizeBeforeUpdate = testFaroukRepository.findAll().size();
        testFarouk.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTestFaroukMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(testFarouk)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTestFaroukWithPatch() throws Exception {
        // Initialize the database
        testFaroukRepository.saveAndFlush(testFarouk);

        int databaseSizeBeforeUpdate = testFaroukRepository.findAll().size();

        // Update the testFarouk using partial update
        TestFarouk partialUpdatedTestFarouk = new TestFarouk();
        partialUpdatedTestFarouk.setId(testFarouk.getId());

        partialUpdatedTestFarouk.adresse(UPDATED_ADRESSE).history(UPDATED_HISTORY);

        restTestFaroukMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTestFarouk.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTestFarouk))
            )
            .andExpect(status().isOk());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeUpdate);
        TestFarouk testTestFarouk = testFaroukList.get(testFaroukList.size() - 1);
        assertThat(testTestFarouk.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTestFarouk.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testTestFarouk.getHistory()).isEqualTo(UPDATED_HISTORY);
    }

    @Test
    @Transactional
    void fullUpdateTestFaroukWithPatch() throws Exception {
        // Initialize the database
        testFaroukRepository.saveAndFlush(testFarouk);

        int databaseSizeBeforeUpdate = testFaroukRepository.findAll().size();

        // Update the testFarouk using partial update
        TestFarouk partialUpdatedTestFarouk = new TestFarouk();
        partialUpdatedTestFarouk.setId(testFarouk.getId());

        partialUpdatedTestFarouk.nom(UPDATED_NOM).adresse(UPDATED_ADRESSE).history(UPDATED_HISTORY);

        restTestFaroukMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTestFarouk.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTestFarouk))
            )
            .andExpect(status().isOk());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeUpdate);
        TestFarouk testTestFarouk = testFaroukList.get(testFaroukList.size() - 1);
        assertThat(testTestFarouk.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTestFarouk.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testTestFarouk.getHistory()).isEqualTo(UPDATED_HISTORY);
    }

    @Test
    @Transactional
    void patchNonExistingTestFarouk() throws Exception {
        int databaseSizeBeforeUpdate = testFaroukRepository.findAll().size();
        testFarouk.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTestFaroukMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, testFarouk.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(testFarouk))
            )
            .andExpect(status().isBadRequest());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTestFarouk() throws Exception {
        int databaseSizeBeforeUpdate = testFaroukRepository.findAll().size();
        testFarouk.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTestFaroukMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(testFarouk))
            )
            .andExpect(status().isBadRequest());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTestFarouk() throws Exception {
        int databaseSizeBeforeUpdate = testFaroukRepository.findAll().size();
        testFarouk.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTestFaroukMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(testFarouk))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TestFarouk in the database
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTestFarouk() throws Exception {
        // Initialize the database
        testFaroukRepository.saveAndFlush(testFarouk);

        int databaseSizeBeforeDelete = testFaroukRepository.findAll().size();

        // Delete the testFarouk
        restTestFaroukMockMvc
            .perform(delete(ENTITY_API_URL_ID, testFarouk.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TestFarouk> testFaroukList = testFaroukRepository.findAll();
        assertThat(testFaroukList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
