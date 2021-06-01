package com.siga.constat.repository;

import com.siga.constat.domain.VictimeDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VictimeDemandeRepository extends JpaRepository<VictimeDemande, Long> {}
