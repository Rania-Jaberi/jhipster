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



import com.siga.constat.domain.ChefAgence;
import com.siga.constat.domain.repository.ChefAgenceRepository;

@RestController
@RequestMapping("/api")
public class ChefAgenceResource {


@Autowired
private ChefAgenceRepository chefagenceRepository;



/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/chefagence")
 public ChefAgence addChefAgence(@RequestBody ChefAgence chefagence)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 chefagence.setDateOp(LocalDateTime.now());
	 chefagence.setUtil("admin");
	 chefagence.setOp("A"); // D,E
	 chefagence= chefagenceRepository.save(chefagence);
 }
 catch(Exception e)
 {
 
 }
 return chefagence;
 }
 
 
 
 @PutMapping("/chefagence")
 public ChefAgence editChefAgence(@RequestBody ChefAgence chefagence)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 chefagence.setUtil("admin");
	 chefagence.setOp("E"); // D,E
	 chefagence= chefagenceRepository.save(chefagence);
 }
 catch(Exception e)
 {
 
 }
 return chefagence;
 }
 
 
 
 @GetMapping("/chefagence/{id}")
 public ChefAgence getChefAgence(@PathVariable("id") Long id)
 {
 try
 {
 return chefagenceRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/chefagence")
 public List< ChefAgence> getAllChefAgences()
 {
 try
 {
 return chefagenceRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/chefagence/{id}")
 public boolean deleteChefAgence(@PathVariable("id") Long id)
 {
 try
 {
	 ChefAgence chefagence= chefagenceRepository.findById(id).get();

	 chefagence.setDateOp(LocalDateTime.now());
	 chefagence.setUtil("admin");
	 chefagence.setOp("D"); // D,E
	 chefagenceRepository.delete(chefagence);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }}
 
 
 
 
 