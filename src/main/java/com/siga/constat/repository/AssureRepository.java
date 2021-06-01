package com.siga.constat.repository;

import com.siga.constat.domain.Assure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssureRepository extends JpaRepository<Assure, Long> {}
