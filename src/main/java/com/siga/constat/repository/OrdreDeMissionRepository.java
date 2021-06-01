package com.siga.constat.repository;

import com.siga.constat.domain.OrdreDeMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdreDeMissionRepository extends JpaRepository<OrdreDeMission, Long> {
    String System = null;
}
