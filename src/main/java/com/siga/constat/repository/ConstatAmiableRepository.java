package com.siga.constat.repository;

import com.siga.constat.domain.ConstatAmiable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstatAmiableRepository extends JpaRepository<ConstatAmiable, Long> {}
