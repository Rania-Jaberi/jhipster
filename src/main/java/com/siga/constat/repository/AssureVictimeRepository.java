package com.siga.constat.repository;

import com.siga.constat.domain.AssureVictime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssureVictimeRepository extends JpaRepository<AssureVictime, Long> {}
