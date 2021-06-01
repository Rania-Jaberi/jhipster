package com.siga.constat.repository;

import com.siga.constat.domain.DemandeVoiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeVoitureRepository extends JpaRepository<DemandeVoiture, Long> {}
