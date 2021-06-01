package com.siga.constat.repository;

import com.siga.constat.domain.Pphysique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PphysiqueRepository extends JpaRepository<Pphysique, Long> {}
