package com.siga.constat.repository;

import com.siga.constat.domain.ExpertSinistre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertSinistreRepository extends JpaRepository<ExpertSinistre, Long> {}
