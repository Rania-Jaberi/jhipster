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

import com.siga.constat.domain.Dossier;
import com.siga.constat.domain.repository.DossierRepository;


@RestController
@RequestMapping("/api")
public class DossierResource {

	
	@Autowired
	private DossierRepository dossierRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/dossier")
	  public Dossier addDossier(@RequestBody Dossier dossier)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  dossier.setDateOp(LocalDateTime.now());
			  dossier.setUtil("admin");
			  dossier.setOp("A"); // D,E
			  dossier= dossierRepository.save(dossier);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return dossier;
	  }
	  
	  
	  
	  @PutMapping("/dossier")
	  public Dossier editDossier(@RequestBody Dossier dossier)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  dossier.setDateOp(LocalDateTime.now());
			  dossier.setUtil("admin");
			  dossier.setOp("E"); // D,E
			  dossier= dossierRepository.save(dossier);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return dossier;
	  }
	  
	  
	  
	  @GetMapping("/dossier/{id}")
	  public Dossier getDossier(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return dossierRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/dossier")
	  public List<Dossier> getAllDossiers()
	  {
		  try
		  {
			  return dossierRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/dossier/{id}")
	  public boolean deleteDossier(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Dossier dossier= dossierRepository.findById(id).get();

			  dossier.setDateOp(LocalDateTime.now());
			  dossier.setUtil("admin");
			  dossier.setOp("D"); // D,E
			  dossierRepository.delete(dossier);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
