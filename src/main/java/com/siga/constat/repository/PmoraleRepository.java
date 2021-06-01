package com.siga.constat.repository;

import com.siga.constat.domain.Pmorale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PmoraleRepository extends JpaRepository<Pmorale, Long> {}
