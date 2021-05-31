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

import com.siga.constat.domain.Assurance;

import com.siga.constat.domain.repository.AssuranceRepository;


@RestController
@RequestMapping("/api")
public class AssuranceResource {


@Autowired
private AssuranceRepository assuranceRepository;


/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/assurance")
 public Assurance addAssurance(@RequestBody Assurance assurance)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 assurance.setDateOp(LocalDateTime.now());
 assurance.setUtil("admin");
 assurance.setOp("A"); // D,E
 assurance= assuranceRepository.save(assurance);
 }
 catch(Exception e)
 {
 
 }
 return assurance;
 }
 
 
 
 @PutMapping("/assurance")
 public Assurance editAssurance(@RequestBody Assurance assurance)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 assurance.setDateOp(LocalDateTime.now());
 assurance.setUtil("admin");
 assurance.setOp("E"); // D,E
 assurance= assuranceRepository.save(assurance);
 }
 catch(Exception e)
 {
 
 }
 return assurance;
 }
 
 
 
 @GetMapping("/assurance/{id}")
 public Assurance getAssurance(@PathVariable("id") Long id)
 {
 try
 {
 return assuranceRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/assurance")
 public List<Assurance> getAllAssurances()
 {
 try
 {
 return assuranceRepository.findAll(); // SELECT * FROM assurance
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/assurance/{id}")
 public boolean deleteAssurance(@PathVariable("id") Long id)
 {
 try
 {
 Assurance assurance= assuranceRepository.findById(id).get();

 assurance.setDateOp(LocalDateTime.now());
 assurance.setUtil("admin");
 assurance.setOp("D"); // D,E
 assuranceRepository.delete(assurance);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }
 
 
 
 
 
 
}


