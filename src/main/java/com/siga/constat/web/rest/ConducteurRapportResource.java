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



import com.siga.constat.domain.ConducteurRapport;
import com.siga.constat.domain.repository.ConducteurRapportRepository;

@RestController
@RequestMapping("/api")
public class ConducteurRapportResource {


@Autowired
private ConducteurRapportRepository conducteurrapportRepository;



/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/conducteurrapport")
 public ConducteurRapport addConducteurRapport(@RequestBody ConducteurRapport conducteurrapport)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 conducteurrapport.setDateOp(LocalDateTime.now());
	 conducteurrapport.setUtil("admin");
	 conducteurrapport.setOp("A"); // D,E
	 conducteurrapport= conducteurrapportRepository.save(conducteurrapport);
 }
 catch(Exception e)
 {
 
 }
 return conducteurrapport;
 }
 
 
 
 @PutMapping("/conducteurrapport")
 public ConducteurRapport editConducteurRapport(@RequestBody ConducteurRapport conducteurrapport)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 conducteurrapport.setUtil("admin");
	 conducteurrapport.setOp("E"); // D,E
	 conducteurrapport=conducteurrapportRepository.save(conducteurrapport);
 }
 catch(Exception e)
 {
 
 }
 return conducteurrapport;
 }
 
 
 
 @GetMapping("/conducteurrapport/{id}")
 public ConducteurRapport getConducteurRapport(@PathVariable("id") Long id)
 {
 try
 {
 return conducteurrapportRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/conducteurrapport")
 public List< ConducteurRapport> getAllConducteurrapports()
 {
 try
 {
 return conducteurrapportRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/conducteurrapport/{id}")
 public boolean deleteConducteurRapport(@PathVariable("id") Long id)
 {
 try
 {
	 ConducteurRapport conducteurrapport= conducteurrapportRepository.findById(id).get();

	 conducteurrapport.setDateOp(LocalDateTime.now());
	 conducteurrapport.setUtil("admin");
	 conducteurrapport.setOp("D"); // D,E
	 conducteurrapportRepository.delete(conducteurrapport);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }}
 
 
 
