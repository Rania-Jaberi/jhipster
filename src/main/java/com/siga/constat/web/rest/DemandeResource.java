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
import com.siga.constat.domain.Demande;
import com.siga.constat.domain.repository.AgenceRepository;
import com.siga.constat.domain.repository.DemandeRepository;

@RestController
@RequestMapping("/api")
public class DemandeResource {


@Autowired
private DemandeRepository demandeRepository;


/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/demande")
 public Demande addDemande(@RequestBody Demande demande)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 demande.setDateOp(LocalDateTime.now());
 demande.setUtil("admin");
 demande.setOp("A"); // D,E
 demande= demandeRepository.save(demande);
 }
 catch(Exception e)
 {
 
 }
 return demande;
 }
 
 
 
 @PutMapping("/demande")
 public Demande editDemande(@RequestBody Demande demande)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 demande.setDateOp(LocalDateTime.now());
 demande.setUtil("admin");
 demande.setOp("E"); // D,E
 demande=demandeRepository.save(demande);
 }
 catch(Exception e)
 {
 
 }
 return demande;
 }
 
 
 
 @GetMapping("/demande/{id}")
 public Demande getDemande(@PathVariable("id") Long id)
 {
 try
 {
 return demandeRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/demande")
 public List<Demande> getAllDemandes()
 {
 try
 {
 return demandeRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/demande/{id}")
 public boolean deleteDemande(@PathVariable("id") Long id)
 {
 try
 {
 Demande demande= demandeRepository.findById(id).get();

 demande.setDateOp(LocalDateTime.now());
 demande.setUtil("admin");
 demande.setOp("D"); // D,E
 demandeRepository.delete(demande);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }
 
 
 
 
 
 
}