package com.siga.constat.repository;

import com.siga.constat.domain.Indemnisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndemnisationRepository extends JpaRepository<Indemnisation, Long> {}
