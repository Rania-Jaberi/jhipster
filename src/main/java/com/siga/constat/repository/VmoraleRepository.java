package com.siga.constat.repository;

import com.siga.constat.domain.Vmorale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VmoraleRepository extends JpaRepository<Vmorale, Long> {}
