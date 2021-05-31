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


import com.siga.constat.domain.AssureRapport;
import com.siga.constat.domain.repository.AssureRapportRepository;

@RestController
@RequestMapping("/api")
public class AssureRapportResource {


@Autowired
private AssureRapportRepository assurerapportRepository;



/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/assurerapport")
 public AssureRapport addAssureRapport(@RequestBody AssureRapport assurerapport)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 assurerapport.setDateOp(LocalDateTime.now());
 assurerapport.setUtil("admin");
 assurerapport.setOp("A"); // D,E
 assurerapport= assurerapportRepository.save(assurerapport);
 }
 catch(Exception e)
 {
 
 }
 return assurerapport;
 }
 
 
 
 @PutMapping("/assurerapport")
 public AssureRapport editAssureRapport(@RequestBody AssureRapport assurerapport)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 assurerapport.setDateOp(LocalDateTime.now());
 assurerapport.setUtil("admin");
 assurerapport.setOp("E"); // D,E
 assurerapport= assurerapportRepository.save(assurerapport);
 }
 catch(Exception e)
 {
 
 }
 return assurerapport;
 }
 
 
 
 @GetMapping("/assurerapport/{id}")
 public AssureRapport getAssureRapport(@PathVariable("id") Long id)
 {
 try
 {
 return assurerapportRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/assurerapport")
 public List<AssureRapport> getAllAssuresrapports()
 {
 try
 {
 return assurerapportRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/assurerapport/{id}")
 public boolean deleteAssureRapport(@PathVariable("id") Long id)
 {
 try
 {
 AssureRapport assurerapport= assurerapportRepository.findById(id).get();

 assurerapport.setDateOp(LocalDateTime.now());
 assurerapport.setUtil("admin");
 assurerapport.setOp("D"); // D,E
 assurerapportRepository.delete(assurerapport);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }}
 
 
 
 
 
 



