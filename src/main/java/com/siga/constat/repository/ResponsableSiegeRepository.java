package com.siga.constat.repository;

import com.siga.constat.domain.ResponsableSiege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableSiegeRepository extends JpaRepository<ResponsableSiege, Long> {}
