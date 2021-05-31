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

import com.siga.constat.domain.Gestionnaire;
import com.siga.constat.domain.repository.GestionnaireRepository;


@RestController
@RequestMapping("/api")
public class GestionnaireResource {

	
	@Autowired
	private GestionnaireRepository gestionnaireRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/gestionnaire")
	  public Gestionnaire addGestionnaire(@RequestBody Gestionnaire gestionnaire)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  gestionnaire.setDateOp(LocalDateTime.now());
			  gestionnaire.setUtil("admin");
			  gestionnaire.setOp("A"); // D,E
			  gestionnaire= gestionnaireRepository.save(gestionnaire);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return gestionnaire;
	  }
	  
	  
	  
	  @PutMapping("/gestionnaire")
	  public Gestionnaire editGestionnaire(@RequestBody Gestionnaire gestionnaire)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  gestionnaire.setDateOp(LocalDateTime.now());
			  gestionnaire.setUtil("admin");
			  gestionnaire.setOp("E"); // D,E
			  gestionnaire= gestionnaireRepository.save(gestionnaire);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return gestionnaire;
	  }
	  
	  
	  
	  @GetMapping("/gestionnaire/{id}")
	  public Gestionnaire getGestionnaire(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return gestionnaireRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/gestionnaire")
	  public List<Gestionnaire> getAllGestionnaires()
	  {
		  try
		  {
			  return gestionnaireRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/gestionnaire/{id}")
	  public boolean deleteGestionnaire(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Gestionnaire gestionnaire= gestionnaireRepository.findById(id).get();

			  gestionnaire.setDateOp(LocalDateTime.now());
			  gestionnaire.setUtil("admin");
			  gestionnaire.setOp("D"); // D,E
			  gestionnaireRepository.delete(gestionnaire);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
