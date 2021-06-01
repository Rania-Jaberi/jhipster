package com.siga.constat.repository;

import com.siga.constat.domain.DemandeConducteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeConducteurRepository extends JpaRepository<DemandeConducteur, Long> {}
