package com.siga.constat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siga.constat.domain.Pphysique;
 @Repository
public interface PphysiqueRepository extends JpaRepository<Pphysique,Long> {

}
