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



import com.siga.constat.domain.AssureVictime;
import com.siga.constat.domain.repository.AssureVictimeRepository;

@RestController
@RequestMapping("/api")
public class AssureVictimeResource {


@Autowired
private  AssureVictimeRepository assurevictimeRepository;



/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/assurevictime")
 public  AssureVictime addAssureVictime(@RequestBody  AssureVictime assurevictime)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 assurevictime.setDateOp(LocalDateTime.now());
	 assurevictime.setUtil("admin");
	 assurevictime.setOp("A"); // D,E
	 assurevictime= assurevictimeRepository.save(assurevictime);
 }
 catch(Exception e)
 {
 
 }
 return assurevictime;
 }
 
 
 
 @PutMapping("/assurevictime")
 public AssureVictime editAssureVictime(@RequestBody AssureVictime assurevictime)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
	 assurevictime.setDateOp(LocalDateTime.now());
	 assurevictime.setUtil("admin");
	 assurevictime.setOp("E"); // D,E
	 assurevictime= assurevictimeRepository.save(assurevictime);
 }
 catch(Exception e)
 {
 
 }
 return assurevictime;
 }
 
 
 
 @GetMapping("/assurevictime/{id}")
 public AssureVictime getAssureVictime(@PathVariable("id") Long id)
 {
 try
 {
 return assurevictimeRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/assurevictime")
 public List<AssureVictime> getAllAssuresvictimes()
 {
 try
 {
 return assurevictimeRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/assurevictime/{id}")
 public boolean deleteAssureVictime(@PathVariable("id") Long id)
 {
 try
 {
 AssureVictime assurevictime= assurevictimeRepository.findById(id).get();

 assurevictime.setDateOp(LocalDateTime.now());
 assurevictime.setUtil("admin");
 assurevictime.setOp("D"); // D,E
 assurevictimeRepository.delete(assurevictime);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }}
 
 
 
 
 
 




