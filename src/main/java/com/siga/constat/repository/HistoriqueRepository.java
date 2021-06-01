package com.siga.constat.repository;

import com.siga.constat.domain.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
    String System = null;
}
