package com.siga.constat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siga.constat.domain.Pmorale;
@Repository
public interface PmoraleRepository extends JpaRepository<Pmorale,Long> {

}
