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

import com.siga.constat.domain.DossierConducteur;
import com.siga.constat.domain.repository.DossierConducteurRepository;


@RestController
@RequestMapping("/api")
public class DossierConducteurResource {

	
	@Autowired
	private DossierConducteurRepository dossierconducteurRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/dossierconducteur")
	  public DossierConducteur addDossierConducteur(@RequestBody DossierConducteur dossierconducteur)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  dossierconducteur.setDateOp(LocalDateTime.now());
			  dossierconducteur.setUtil("admin");
			  dossierconducteur.setOp("A"); // D,E
			  dossierconducteur= dossierconducteurRepository.save(dossierconducteur);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return dossierconducteur;
	  }
	  
	  
	  
	  @PutMapping("/dossierconducteur")
	  public DossierConducteur editDossierConducteur(@RequestBody DossierConducteur dossierconducteur)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  dossierconducteur.setDateOp(LocalDateTime.now());
			  dossierconducteur.setUtil("admin");
			  dossierconducteur.setOp("E"); // D,E
			  dossierconducteur= dossierconducteurRepository.save(dossierconducteur);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return dossierconducteur;
	  }
	  
	  
	  
	  @GetMapping("/dossierconducteur/{id}")
	  public DossierConducteur getDossierConducteur(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return dossierconducteurRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/dossierconducteur")
	  public List<DossierConducteur> getAllDossierConducteurs()
	  {
		  try
		  {
			  return dossierconducteurRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/dossierconducteur/{id}")
	  public boolean deleteDossierConducteur(@PathVariable("id") Long id)
	  {
		  try
		  {
			  DossierConducteur dossierconducteur= dossierconducteurRepository.findById(id).get();

			  dossierconducteur.setDateOp(LocalDateTime.now());
			  dossierconducteur.setUtil("admin");
			  dossierconducteur.setOp("D"); // D,E
			  dossierconducteurRepository.delete(dossierconducteur);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
