package com.siga.constat.repository;

import com.siga.constat.domain.RapportVictime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportVictimeRepository extends JpaRepository<RapportVictime, Long> {}
