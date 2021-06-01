package com.siga.constat.repository;

import com.siga.constat.domain.Victime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VictimeRepository extends JpaRepository<Victime, Long> {}
