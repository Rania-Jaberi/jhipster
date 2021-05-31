package com.siga.constat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siga.constat.domain.Historique;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique,Long>{
	String System = null;

}
