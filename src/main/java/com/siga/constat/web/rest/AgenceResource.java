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

import com.siga.constat.domain.Agence;
import com.siga.constat.domain.repository.AgenceRepository;

@RestController
@RequestMapping("/api")
public class AgenceResource {


@Autowired
private AgenceRepository agenceRepository;


/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/agence")
 public Agence addAgence(@RequestBody Agence agence)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 agence.setDateOp(LocalDateTime.now());
 agence.setUtil("admin");
 agence.setOp("A"); // D,E
 agence= agenceRepository.save(agence);
 }
 catch(Exception e)
 {
 
 }
 return agence;
 }
 
 
 
 @PutMapping("/agence")
 public Agence editAgence(@RequestBody Agence agence)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 agence.setDateOp(LocalDateTime.now());
 agence.setUtil("admin");
 agence.setOp("E"); // D,E
 agence= agenceRepository.save(agence);
 }
 catch(Exception e)
 {
 
 }
 return agence;
 }
 
 
 
 @GetMapping("/agence/{id}")
 public Agence getAgence(@PathVariable("id") Long id)
 {
 try
 {
 return agenceRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/agence")
 public List<Agence> getAllAgences()
 {
 try
 {
 return agenceRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/agence/{id}")
 public boolean deleteAgence(@PathVariable("id") Long id)
 {
 try
 {
 Agence agence= agenceRepository.findById(id).get();

 agence.setDateOp(LocalDateTime.now());
 agence.setUtil("admin");
 agence.setOp("D"); // D,E
 agenceRepository.delete(agence);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }
 
 
 
 
 
 
}