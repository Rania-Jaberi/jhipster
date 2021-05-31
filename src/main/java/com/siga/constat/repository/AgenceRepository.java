package com.siga.constat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siga.constat.domain.Agence;

@Repository

public interface AgenceRepository extends JpaRepository <Agence,Long> {
	
	
	String System = null;

}
