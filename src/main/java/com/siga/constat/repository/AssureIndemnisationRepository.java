package com.siga.constat.repository;

import com.siga.constat.domain.AssureIndemnisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssureIndemnisationRepository extends JpaRepository<AssureIndemnisation, Long> {}
