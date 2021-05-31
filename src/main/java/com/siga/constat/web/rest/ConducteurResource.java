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



import com.siga.constat.domain.Conducteur;
import com.siga.constat.domain.repository.ConducteurRepository;

@RestController
@RequestMapping("/api")
public class ConducteurResource {


@Autowired
private ConducteurRepository conducteurRepository;



/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/conducteur")
 public Conducteur addConducteur(@RequestBody Conducteur conducteur)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 conducteur.setDateOp(LocalDateTime.now());
	 conducteur.setUtil("admin");
	 conducteur.setOp("A"); // D,E
	 conducteur= conducteurRepository.save(conducteur);
 }
 catch(Exception e)
 {
 
 }
 return conducteur;
 }
 
 
 
 @PutMapping("/conducteur")
 public Conducteur editConducteur(@RequestBody Conducteur conducteur)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 conducteur.setUtil("admin");
	 conducteur.setOp("E"); // D,E
	 conducteur=conducteurRepository.save(conducteur);
 }
 catch(Exception e)
 {
 
 }
 return conducteur;
 }
 
 
 
 @GetMapping("/conducteur/{id}")
 public Conducteur getConducteur(@PathVariable("id") Long id)
 {
 try
 {
 return conducteurRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/conducteur")
 public List<Conducteur> getAllConducteurs()
 {
 try
 {
 return conducteurRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/conducteur/{id}")
 public boolean deleteConducteur(@PathVariable("id") Long id)
 {
 try
 {
	 Conducteur conducteur= conducteurRepository.findById(id).get();

	 conducteur.setDateOp(LocalDateTime.now());
	 conducteur.setUtil("admin");
	 conducteur.setOp("D"); // D,E
	 conducteurRepository.delete(conducteur);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }}
 
 
 
