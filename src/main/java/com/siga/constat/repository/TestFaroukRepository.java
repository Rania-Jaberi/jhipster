package com.siga.constat.repository;

import com.siga.constat.domain.TestFarouk;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TestFarouk entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestFaroukRepository extends JpaRepository<TestFarouk, Long> {}
