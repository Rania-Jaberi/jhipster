package com.siga.constat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siga.constat.domain.OrdreDeMission;

@Repository
public interface OrdreDeMissionRepository extends JpaRepository<OrdreDeMission ,Long> {
	
	String System = null;
}
