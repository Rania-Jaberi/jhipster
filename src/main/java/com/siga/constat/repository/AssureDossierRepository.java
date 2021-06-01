package com.siga.constat.repository;

import com.siga.constat.domain.AssureDossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssureDossierRepository extends JpaRepository<AssureDossier, Long> {}
