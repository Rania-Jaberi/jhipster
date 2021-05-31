package com.siga.constat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siga.constat.domain.Assurance;
@Repository
public interface AssuranceRepository extends JpaRepository<Assurance,Long> {
	String System = null;

}
