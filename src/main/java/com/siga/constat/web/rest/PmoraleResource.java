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

import com.siga.constat.domain.Pmorale;
import com.siga.constat.domain.repository.PmoraleRepository;

@RestController
@RequestMapping("/api")
public class PmoraleResource {

	
	@Autowired
	private PmoraleRepository pmoraleRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/pmorale")
	  public Pmorale addPmorale(@RequestBody Pmorale pmorale)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  pmorale.setDateOp(LocalDateTime.now());
			  pmorale.setUtil("admin");
			  pmorale.setOp("A"); // D,E
			  pmorale= pmoraleRepository.save(pmorale);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return pmorale;
	  }
	  
	  
	  
	  @PutMapping("/pmorale")
	  public Pmorale editPphysique(@RequestBody Pmorale pmorale)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  pmorale.setDateOp(LocalDateTime.now());
			  pmorale.setUtil("admin");
			  pmorale.setOp("E"); // D,E
			  pmorale= pmoraleRepository.save(pmorale);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return pmorale;
	  }
	  
	  
	  
	  @GetMapping("/pmorale/{id}")
	  public Pmorale getPmorale(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return pmoraleRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/pmorale")
	  public List<Pmorale> getAllPmorales()
	  {
		  try
		  {
			  return pmoraleRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/pmorale/{id}")
	  public boolean deletePmorale(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Pmorale pmorale= pmoraleRepository.findById(id).get();

			  pmorale.setDateOp(LocalDateTime.now());
			  pmorale.setUtil("admin");
			  pmorale.setOp("D"); // D,E
			  pmoraleRepository.delete(pmorale);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}

