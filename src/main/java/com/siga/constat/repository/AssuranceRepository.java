package com.siga.constat.repository;

import com.siga.constat.domain.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
    String System = null;
}
