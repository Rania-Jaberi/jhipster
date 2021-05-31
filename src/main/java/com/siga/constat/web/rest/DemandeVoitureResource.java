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

import com.siga.constat.domain.DemandeVoiture;
import com.siga.constat.domain.repository.DemandeVoitureRepository;

@RestController
@RequestMapping("/api")
public class DemandeVoitureResource {


@Autowired
private DemandeVoitureRepository demandevoitureRepository;


/*
*  les webservice : ajout/consultation/modification/suppression
*    CRUD :
*    
*    
*    
* */


 @PostMapping("/demandevoiture")
 public DemandeVoiture addDemandeVoiture(@RequestBody DemandeVoiture demandevoiture)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 demandevoiture.setDateOp(LocalDateTime.now());
 demandevoiture.setUtil("admin");
 demandevoiture.setOp("A"); // D,E
 demandevoiture= demandevoitureRepository.save(demandevoiture);
 }
 catch(Exception e)
 {
 
 }
 return demandevoiture;
 }
 
 
 
 @PutMapping("/demandevoiture")
 public DemandeVoiture editDemandeVoiture(@RequestBody DemandeVoiture demandevoiture)
 {
 try
 {
   
 /* ajout traitement spécial :
  * setDateCreate setCreatedBy
  * */
 demandevoiture.setDateOp(LocalDateTime.now());
 demandevoiture.setUtil("admin");
 demandevoiture.setOp("E"); // D,E
 demandevoiture= demandevoitureRepository.save(demandevoiture);
 }
 catch(Exception e)
 {
 
 }
 return demandevoiture;
 }
 
 
 
 @GetMapping("/demandevoiture/{id}")
 public DemandeVoiture getDemandeVoiture(@PathVariable("id") Long id)
 {
 try
 {
 return demandevoitureRepository.findById(id).get();
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 
 @GetMapping("/demandevoiture")
 public List<DemandeVoiture>getAllDemandesvoitures()
 {
 try
 {
 return demandevoitureRepository.findAll(); // SELECT * FROM agence
 }
 catch(Exception e)
 {
 
 }
 return null;
 }
 
 

 
 
 
 
 @DeleteMapping("/demandevoiture/{id}")
 public boolean deleteDemandeVoiture(@PathVariable("id") Long id)
 {
 try
 {
  DemandeVoiture demandevoiture= demandevoitureRepository.findById(id).get();

 demandevoiture.setDateOp(LocalDateTime.now());
 demandevoiture.setUtil("admin");
 demandevoiture.setOp("D"); // D,E
 demandevoitureRepository.delete(demandevoiture);
 return true;
 }
 catch(Exception e)
 {
 
 }
 return false;
 }
 
 
 
 
 
 
}