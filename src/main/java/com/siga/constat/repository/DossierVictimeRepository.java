package com.siga.constat.repository;

import com.siga.constat.domain.DossierVictime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierVictimeRepository extends JpaRepository<DossierVictime, Long> {}
