package com.siga.constat.repository;

import com.siga.constat.domain.ChefAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefAgenceRepository extends JpaRepository<ChefAgence, Long> {}
