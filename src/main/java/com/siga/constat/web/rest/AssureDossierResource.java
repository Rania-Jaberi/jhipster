package com.siga.constat.domain.resource;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siga.constat.domain.AssureDossier;
import com.siga.constat.domain.repository.AssureDossierRepository;

@RestController
@RequestMapping("/api")
public class AssureDossierResource {


@Autowired
private AssureDossierRepository assuredossierRepository;



/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/assuredossier")
 public AssureDossier addAssureDossier(@RequestBody AssureDossier assuredossier)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 assuredossier.setDateOp(LocalDateTime.now());
 assuredossier.setUtil("admin");
 assuredossier.setOp("A"); // D,E
 assuredossier= assuredossierRepository.save(assuredossier);
 }
 catch(Exception e)
 {
 
 }
 return assuredossier;
 }
 
 
 
 @PutMapping("/assuredossier")
 public AssureDossier editAssureDossier(@RequestBody AssureDossier assuredossier)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 assuredossier.setDateOp(LocalDateTime.now());
 assuredossier.setUtil("admin");
 assuredossier.setOp("E"); // D,E
 assuredossier= assuredossierRepository.save(assuredossier);
 }
 catch(Exception e)
 {
 
 }
 return assuredossier;
 }
 
 
 
 @GetMapping("/assuredossier/{id}")
 public AssureDossier getAssureDossier(@PathVariable("id") Long id)
 {
 try
 {
 return assuredossierRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/assuredossier")
 public List<AssureDossier> getAllAssuresdossiers()
 {
 try
 {
 return assuredossierRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/assuredossier/{id}")
 public boolean deleteAssureDossier(@PathVariable("id") Long id)
 {
 try
 {
 AssureDossier assuredossier= assuredossierRepository.findById(id).get();

 assuredossier.setDateOp(LocalDateTime.now());
 assuredossier.setUtil("admin");
 assuredossier.setOp("D"); // D,E
 assuredossierRepository.delete(assuredossier);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }}
 
 
 
 
 
 
