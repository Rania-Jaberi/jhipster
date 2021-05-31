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

import com.siga.constat.domain.Indemnisation;
import com.siga.constat.domain.repository.IndemnisationRepository;


@RestController
@RequestMapping("/api")
public class IndemnisationResource {

	
	@Autowired
	private IndemnisationRepository indemnisationRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/indemnisation")
	  public Indemnisation addIndemnisation(@RequestBody Indemnisation indemnisation)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  indemnisation.setDateOp(LocalDateTime.now());
			  indemnisation.setUtil("admin");
			  indemnisation.setOp("A"); // D,E
			  indemnisation= indemnisationRepository.save(indemnisation);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return indemnisation;
	  }
	  
	  
	  
	  @PutMapping("/indemnisation")
	  public Indemnisation editIndemnisation(@RequestBody Indemnisation indemnisation)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  indemnisation.setDateOp(LocalDateTime.now());
			  indemnisation.setUtil("admin");
			  indemnisation.setOp("E"); // D,E
			  indemnisation= indemnisationRepository.save(indemnisation);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return indemnisation;
	  }
	  
	  
	  
	  @GetMapping("/indemnisation/{id}")
	  public Indemnisation getIndemnisation(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return indemnisationRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/indemnisation")
	  public List<Indemnisation> getAllIndemnisations()
	  {
		  try
		  {
			  return indemnisationRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/indemnisation/{id}")
	  public boolean deleteIndemnisation(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Indemnisation indemnisation= indemnisationRepository.findById(id).get();

			  indemnisation.setDateOp(LocalDateTime.now());
			  indemnisation.setUtil("admin");
			  indemnisation.setOp("D"); // D,E
			  indemnisationRepository.delete(indemnisation);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
