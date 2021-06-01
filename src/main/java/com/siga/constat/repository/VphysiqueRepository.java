package com.siga.constat.repository;

import com.siga.constat.domain.Vphysique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VphysiqueRepository extends JpaRepository<Vphysique, Long> {}
