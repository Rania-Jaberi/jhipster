package com.siga.constat.repository;

import com.siga.constat.domain.Conducteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConducteurRepository extends JpaRepository<Conducteur, Long> {}
