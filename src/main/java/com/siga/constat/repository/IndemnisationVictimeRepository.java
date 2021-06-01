package com.siga.constat.repository;

import com.siga.constat.domain.IndemnisationVictime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndemnisationVictimeRepository extends JpaRepository<IndemnisationVictime, Long> {}
