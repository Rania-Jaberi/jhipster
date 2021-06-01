package com.siga.constat.repository;

import com.siga.constat.domain.DossierConducteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierConducteurRepository extends JpaRepository<DossierConducteur, Long> {}
