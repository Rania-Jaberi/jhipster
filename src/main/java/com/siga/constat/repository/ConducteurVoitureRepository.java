package com.siga.constat.repository;

import com.siga.constat.domain.ConducteurVoiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConducteurVoitureRepository extends JpaRepository<ConducteurVoiture, Long> {}
