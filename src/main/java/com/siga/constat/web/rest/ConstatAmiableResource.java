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

import com.siga.constat.domain.ConstatAmiable;
import com.siga.constat.domain.repository.ConstatAmiableRepository;

@RestController
@RequestMapping("/api")
public class ConstatAmiableResource {


@Autowired
private ConstatAmiableRepository constatamiableRepository;



/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
*
*/


 @PostMapping("/constatamiable")
 public  ConstatAmiable addConstatAmiable(@RequestBody ConstatAmiable constatamiable)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 constatamiable.setDateOp(LocalDateTime.now());
	 constatamiable.setUtil("admin");
	 constatamiable.setOp("A"); // D,E
	 constatamiable=constatamiableRepository.save(constatamiable);
 }
 catch(Exception e)
 {
 
 }
 return constatamiable;
 }
 
 
 
 @PutMapping("/constatamiable")
 public ConstatAmiable editConstatAmiable(@RequestBody ConstatAmiable constatamiable)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 constatamiable.setUtil("admin");
	 constatamiable.setOp("E"); // D,E
	 constatamiable=constatamiableRepository.save(constatamiable);
 }
 catch(Exception e)
 {
 
 }
 return constatamiable;
 }
 
 
 
 @GetMapping("/constatamiable/{id}")
 public ConstatAmiable getConstatAmiable(@PathVariable("id") Long id)
 {
 try
 {
 return constatamiableRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/constatamiable")
 public List<ConstatAmiable> getAllConstatamiables()
 {
 try
 {
 return constatamiableRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/constatamiable/{id}")
 public boolean deleteConstatamiable(@PathVariable("id") Long id)
 {
 try
 {
	 ConstatAmiable constatamiable= constatamiableRepository.findById(id).get();

	 constatamiable.setDateOp(LocalDateTime.now());
	 constatamiable.setUtil("admin");
	 constatamiable.setOp("D"); // D,E
	 constatamiableRepository.delete(constatamiable);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }}
 
 
 
