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



import com.siga.constat.domain.Comptable;
import com.siga.constat.domain.repository.ComptableRepository;

@RestController
@RequestMapping("/api")
public class ComptableResource {


@Autowired
private ComptableRepository comptableRepository;



/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/comptable")
 public Comptable addComptable(@RequestBody Comptable comptable)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 comptable.setDateOp(LocalDateTime.now());
	 comptable.setUtil("admin");
	 comptable.setOp("A"); // D,E
	 comptable= comptableRepository.save(comptable);
 }
 catch(Exception e)
 {
 
 }
 return comptable;
 }
 
 
 
 @PutMapping("/comptable")
 public Comptable editComptable(@RequestBody Comptable comptable)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 comptable.setUtil("admin");
	 comptable.setOp("E"); // D,E
	 comptable= comptableRepository.save(comptable);
 }
 catch(Exception e)
 {
 
 }
 return comptable;
 }
 
 
 
 @GetMapping("/comptable/{id}")
 public Comptable getComptable(@PathVariable("id") Long id)
 {
 try
 {
 return comptableRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/comptable")
 public List< Comptable> getAllComptables()
 {
 try
 {
 return comptableRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/comptable/{id}")
 public boolean deleteChefAgence(@PathVariable("id") Long id)
 {
 try
 {
	 Comptable comptable= comptableRepository.findById(id).get();

	 comptable.setDateOp(LocalDateTime.now());
	 comptable.setUtil("admin");
	 comptable.setOp("D"); // D,E
	 comptableRepository.delete(comptable);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }}
 
 
 
 