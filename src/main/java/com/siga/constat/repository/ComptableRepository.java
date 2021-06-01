package com.siga.constat.repository;

import com.siga.constat.domain.Comptable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComptableRepository extends JpaRepository<Comptable, Long> {}
