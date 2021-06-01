package com.siga.constat.repository;

import com.siga.constat.domain.Gestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Long> {
    String System = null;
}
