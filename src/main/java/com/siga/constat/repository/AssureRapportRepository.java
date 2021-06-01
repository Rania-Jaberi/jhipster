package com.siga.constat.repository;

import com.siga.constat.domain.AssureRapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssureRapportRepository extends JpaRepository<AssureRapport, Long> {}
