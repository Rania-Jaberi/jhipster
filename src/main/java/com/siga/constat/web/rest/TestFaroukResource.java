package com.siga.constat.web.rest;

import com.siga.constat.domain.TestFarouk;
import com.siga.constat.repository.TestFaroukRepository;
import com.siga.constat.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.siga.constat.domain.TestFarouk}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TestFaroukResource {

    private final Logger log = LoggerFactory.getLogger(TestFaroukResource.class);

    private static final String ENTITY_NAME = "testFarouk";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestFaroukRepository testFaroukRepository;

    public TestFaroukResource(TestFaroukRepository testFaroukRepository) {
        this.testFaroukRepository = testFaroukRepository;
    }

    /**
     * {@code POST  /test-farouks} : Create a new testFarouk.
     *
     * @param testFarouk the testFarouk to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testFarouk, or with status {@code 400 (Bad Request)} if the testFarouk has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/test-farouks")
    public ResponseEntity<TestFarouk> createTestFarouk(@RequestBody TestFarouk testFarouk) throws URISyntaxException {
        log.debug("REST request to save TestFarouk : {}", testFarouk);
        if (testFarouk.getId() != null) {
            throw new BadRequestAlertException("A new testFarouk cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestFarouk result = testFaroukRepository.save(testFarouk);
        return ResponseEntity
            .created(new URI("/api/test-farouks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /test-farouks/:id} : Updates an existing testFarouk.
     *
     * @param id the id of the testFarouk to save.
     * @param testFarouk the testFarouk to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testFarouk,
     * or with status {@code 400 (Bad Request)} if the testFarouk is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testFarouk couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/test-farouks/{id}")
    public ResponseEntity<TestFarouk> updateTestFarouk(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TestFarouk testFarouk
    ) throws URISyntaxException {
        log.debug("REST request to update TestFarouk : {}, {}", id, testFarouk);
        if (testFarouk.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, testFarouk.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!testFaroukRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TestFarouk result = testFaroukRepository.save(testFarouk);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testFarouk.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /test-farouks/:id} : Partial updates given fields of an existing testFarouk, field will ignore if it is null
     *
     * @param id the id of the testFarouk to save.
     * @param testFarouk the testFarouk to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testFarouk,
     * or with status {@code 400 (Bad Request)} if the testFarouk is not valid,
     * or with status {@code 404 (Not Found)} if the testFarouk is not found,
     * or with status {@code 500 (Internal Server Error)} if the testFarouk couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/test-farouks/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TestFarouk> partialUpdateTestFarouk(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TestFarouk testFarouk
    ) throws URISyntaxException {
        log.debug("REST request to partial update TestFarouk partially : {}, {}", id, testFarouk);
        if (testFarouk.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, testFarouk.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!testFaroukRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TestFarouk> result = testFaroukRepository
            .findById(testFarouk.getId())
            .map(
                existingTestFarouk -> {
                    if (testFarouk.getNom() != null) {
                        existingTestFarouk.setNom(testFarouk.getNom());
                    }
                    if (testFarouk.getAdresse() != null) {
                        existingTestFarouk.setAdresse(testFarouk.getAdresse());
                    }
                    if (testFarouk.getHistory() != null) {
                        existingTestFarouk.setHistory(testFarouk.getHistory());
                    }

                    return existingTestFarouk;
                }
            )
            .map(testFaroukRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testFarouk.getId().toString())
        );
    }

    /**
     * {@code GET  /test-farouks} : get all the testFarouks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testFarouks in body.
     */
    @GetMapping("/test-farouks")
    public List<TestFarouk> getAllTestFarouks() {
        log.debug("REST request to get all TestFarouks");
        return testFaroukRepository.findAll();
    }

    /**
     * {@code GET  /test-farouks/:id} : get the "id" testFarouk.
     *
     * @param id the id of the testFarouk to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testFarouk, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/test-farouks/{id}")
    public ResponseEntity<TestFarouk> getTestFarouk(@PathVariable Long id) {
        log.debug("REST request to get TestFarouk : {}", id);
        Optional<TestFarouk> testFarouk = testFaroukRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(testFarouk);
    }

    /**
     * {@code DELETE  /test-farouks/:id} : delete the "id" testFarouk.
     *
     * @param id the id of the testFarouk to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/test-farouks/{id}")
    public ResponseEntity<Void> deleteTestFarouk(@PathVariable Long id) {
        log.debug("REST request to delete TestFarouk : {}", id);
        testFaroukRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
