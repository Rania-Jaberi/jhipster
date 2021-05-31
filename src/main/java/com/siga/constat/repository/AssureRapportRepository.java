package com.siga.constat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siga.constat.domain.AssureRapport;

@Repository
public interface AssureRapportRepository extends JpaRepository<AssureRapport,Long>{

}
