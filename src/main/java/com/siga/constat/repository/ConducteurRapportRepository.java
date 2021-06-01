package com.siga.constat.repository;

import com.siga.constat.domain.ConducteurRapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConducteurRapportRepository extends JpaRepository<ConducteurRapport, Long> {}
