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
import com.siga.constat.domain.ConducteurVoiture;
import com.siga.constat.domain.repository.ConducteurVoitureRepository;

@RestController
@RequestMapping("/api")
public class ConducteurVoitureResource {


@Autowired
private ConducteurVoitureRepository conducteurvoitureRepository;



/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/conducteurvoiture")
 public ConducteurVoiture addConducteurVoiture(@RequestBody ConducteurVoiture conducteurvoiture)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 conducteurvoiture.setDateOp(LocalDateTime.now());
	 conducteurvoiture.setUtil("admin");
	 conducteurvoiture.setOp("A"); // D,E
	 conducteurvoiture= conducteurvoitureRepository.save(conducteurvoiture);
 }
 catch(Exception e)
 {
 
 }
 return conducteurvoiture;
 }
 
 
 
 @PutMapping("/conducteurvoiture")
 public ConducteurVoiture editConducteurVoiture(@RequestBody ConducteurVoiture conducteurvoiture)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 conducteurvoiture.setUtil("admin");
	 conducteurvoiture.setOp("E"); // D,E
	 conducteurvoiture=conducteurvoitureRepository.save(conducteurvoiture);
 }
 catch(Exception e)
 {
 
 }
 return conducteurvoiture;
 }
 
 
 
 @GetMapping("/conducteurvoiture/{id}")
 public ConducteurVoiture getConducteurVoiture(@PathVariable("id") Long id)
 {
 try
 {
 return conducteurvoitureRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/conducteurvoiture")
 public List< ConducteurVoiture> getAllConducteursvoitures()
 {
 try
 {
 return conducteurvoitureRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/conducteurvoiture/{id}")
 public boolean deleteConducteurVoiture(@PathVariable("id") Long id)
 {
 try
 {
	 ConducteurVoiture conducteurvoiture= conducteurvoitureRepository.findById(id).get();

	 conducteurvoiture.setDateOp(LocalDateTime.now());
	 conducteurvoiture.setUtil("admin");
	 conducteurvoiture.setOp("D"); // D,E
	 conducteurvoitureRepository.delete(conducteurvoiture);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }}
 
 
 
