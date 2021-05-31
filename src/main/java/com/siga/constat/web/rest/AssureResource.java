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

import com.siga.constat.domain.Assure;
import com.siga.constat.domain.repository.AssureRepository;

@RestController
@RequestMapping("/api")
public class AssureResource {


@Autowired
private AssureRepository assureRepository;


/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/assure")
 public Assure addAssure(@RequestBody Assure assure)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 assure.setDateOp(LocalDateTime.now());
 assure.setUtil("admin");
 assure.setOp("A"); // D,E
 assure= assureRepository.save(assure);
 }
 catch(Exception e)
 {
 
 }
 return assure;
 }
 
 
 
 @PutMapping("/assure")
 public Assure editAssure(@RequestBody Assure assure)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 assure.setDateOp(LocalDateTime.now());
 assure.setUtil("admin");
 assure.setOp("E"); // D,E
 assure= assureRepository.save(assure);
 }
 catch(Exception e)
 {
 
 }
 return assure;
 }
 
 
 
 @GetMapping("/assure/{id}")
 public Assure getAssure(@PathVariable("id") Long id)
 {
 try
 {
 return assureRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/assure")
 public List<Assure> getAllAssures()
 {
 try
 {
 return assureRepository.findAll(); // SELECT * FROM assure
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/assure/{id}")
 public boolean deleteAssure(@PathVariable("id") Long id)
 {
 try
 {
 Assure assure= assureRepository.findById(id).get();

 assure.setDateOp(LocalDateTime.now());
 assure.setUtil("admin");
 assure.setOp("D"); // D,E
 assureRepository.delete(assure);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }
 
 
 
 
 
 

}
