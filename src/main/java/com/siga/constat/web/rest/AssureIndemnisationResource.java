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

import com.siga.constat.domain.AssureIndemnisation;
import com.siga.constat.domain.repository.AssureIndemnisationRepository;

@RestController
@RequestMapping("/api")
public class AssureIndemnisationResource {


@Autowired
private AssureIndemnisationRepository assureindemnisationRepository;



/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/assureindemnisation")
 public AssureIndemnisation addAssureIndemnisation(@RequestBody AssureIndemnisation assureindemnisation)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 assureindemnisation.setDateOp(LocalDateTime.now());
	 assureindemnisation.setUtil("admin");
	 assureindemnisation.setOp("A"); // D,E
	 assureindemnisation= assureindemnisationRepository.save(assureindemnisation);
 }
 catch(Exception e)
 {
 
 }
 return assureindemnisation;
 }
 
 
 
 @PutMapping("/assureindemnisation")
 public AssureIndemnisation editAssureIndemnisation(@RequestBody AssureIndemnisation assureindemnisation)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 assureindemnisation.setDateOp(LocalDateTime.now());
	 assureindemnisation.setUtil("admin");
	 assureindemnisation.setOp("E"); // D,E
	 assureindemnisation= assureindemnisationRepository.save(assureindemnisation);
 }
 catch(Exception e)
 {
 
 }
 return assureindemnisation;
 }
 
 
 
 @GetMapping("/assureindemnisation/{id}")
 public AssureIndemnisation getAssureIndemnisation(@PathVariable("id") Long id)
 {
 try
 {
 return assureindemnisationRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/assureindemnisation")
 public List<AssureIndemnisation> getAllAssureindemnisation()
 {
 try
 {
 return assureindemnisationRepository.findAll(); // SELECT * FROM assureindemnisation
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/assureindemnisation/{id}")
 public boolean deleteAssureIndemnisation(@PathVariable("id") Long id)
 {
 try
 {
 AssureIndemnisation assureindemnisation= assureindemnisationRepository.findById(id).get();

 assureindemnisation.setDateOp(LocalDateTime.now());
 assureindemnisation.setUtil("admin");
 assureindemnisation.setOp("D"); // D,E
 assureindemnisationRepository.delete(assureindemnisation);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }}
 
 
 
 
 
 



