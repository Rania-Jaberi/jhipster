package com.siga.constat.repository;

import com.siga.constat.domain.VoitureVictime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiturevictimeRepository extends JpaRepository<VoitureVictime, Long> {}
