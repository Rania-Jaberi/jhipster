package com.siga.constat.web.rest;

import com.siga.constat.domain.Entry;
import com.siga.constat.repository.EntryRepository;
import com.siga.constat.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.siga.constat.domain.Entry}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EntryResource {

    private final Logger log = LoggerFactory.getLogger(EntryResource.class);

    private static final String ENTITY_NAME = "entry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntryRepository entryRepository;

    public EntryResource(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    /**
     * {@code POST  /entries} : Create a new entry.
     *
     * @param entry the entry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entry, or with status {@code 400 (Bad Request)} if the entry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entries")
    public ResponseEntity<Entry> createEntry(@Valid @RequestBody Entry entry) throws URISyntaxException {
        log.debug("REST request to save Entry : {}", entry);
        if (entry.getId() != null) {
            throw new BadRequestAlertException("A new entry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Entry result = entryRepository.save(entry);
        return ResponseEntity
            .created(new URI("/api/entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entries/:id} : Updates an existing entry.
     *
     * @param id the id of the entry to save.
     * @param entry the entry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entry,
     * or with status {@code 400 (Bad Request)} if the entry is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entries/{id}")
    public ResponseEntity<Entry> updateEntry(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Entry entry)
        throws URISyntaxException {
        log.debug("REST request to update Entry : {}, {}", id, entry);
        if (entry.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entry.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Entry result = entryRepository.save(entry);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entry.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /entries/:id} : Partial updates given fields of an existing entry, field will ignore if it is null
     *
     * @param id the id of the entry to save.
     * @param entry the entry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entry,
     * or with status {@code 400 (Bad Request)} if the entry is not valid,
     * or with status {@code 404 (Not Found)} if the entry is not found,
     * or with status {@code 500 (Internal Server Error)} if the entry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/entries/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Entry> partialUpdateEntry(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Entry entry
    ) throws URISyntaxException {
        log.debug("REST request to partial update Entry partially : {}, {}", id, entry);
        if (entry.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entry.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Entry> result = entryRepository
            .findById(entry.getId())
            .map(
                existingEntry -> {
                    if (entry.getTitle() != null) {
                        existingEntry.setTitle(entry.getTitle());
                    }
                    if (entry.getContent() != null) {
                        existingEntry.setContent(entry.getContent());
                    }
                    if (entry.getDate() != null) {
                        existingEntry.setDate(entry.getDate());
                    }

                    return existingEntry;
                }
            )
            .map(entryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entry.getId().toString())
        );
    }

    /**
     * {@code GET  /entries} : get all the entries.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entries in body.
     */
    @GetMapping("/entries")
    public ResponseEntity<List<Entry>> getAllEntries(
        Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of Entries");
        Page<Entry> page;
        if (eagerload) {
            page = entryRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = entryRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /entries/:id} : get the "id" entry.
     *
     * @param id the id of the entry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entries/{id}")
    public ResponseEntity<Entry> getEntry(@PathVariable Long id) {
        log.debug("REST request to get Entry : {}", id);
        Optional<Entry> entry = entryRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(entry);
    }

    /**
     * {@code DELETE  /entries/:id} : delete the "id" entry.
     *
     * @param id the id of the entry to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        log.debug("REST request to delete Entry : {}", id);
        entryRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
