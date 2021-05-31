package com.siga.constat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siga.constat.domain.Gestionnaire;



@Repository
public interface GestionnaireRepository extends JpaRepository <Gestionnaire,Long> {
	
	
	String System = null;

}
