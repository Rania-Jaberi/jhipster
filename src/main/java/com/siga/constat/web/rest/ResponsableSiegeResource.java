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

import com.siga.constat.domain.ResponsableSiege;
import com.siga.constat.domain.repository.ResponsableSiegeRepository;



@RestController
@RequestMapping("/api")
public class ResponsableSiegeResource {

	
	@Autowired
	private ResponsableSiegeRepository responsablesiegeRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/responsablesiege")
	  public ResponsableSiege addResponsableSiege(@RequestBody ResponsableSiege responsablesiege)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  responsablesiege.setDateOp(LocalDateTime.now());
			  responsablesiege.setUtil("admin");
			  responsablesiege.setOp("A"); // D,E
			  responsablesiege= responsablesiegeRepository.save(responsablesiege);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return responsablesiege;
	  }
	  
	  
	  
	  @PutMapping("/responsablesiege")
	  public ResponsableSiege editResponsableSiege(@RequestBody ResponsableSiege responsablesiege)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  responsablesiege.setDateOp(LocalDateTime.now());
			  responsablesiege.setUtil("admin");
			  responsablesiege.setOp("E"); // D,E
			  responsablesiege= responsablesiegeRepository.save(responsablesiege);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return responsablesiege;
	  }
	  
	  
	  
	  @GetMapping("/responsablesiege/{id}")
	  public ResponsableSiege getResponsableSiege(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return responsablesiegeRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/responsablesiege")
	  public List<ResponsableSiege> getAllResponsableSieges()
	  {
		  try
		  {
			  return responsablesiegeRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/responsablesiege/{id}")
	  public boolean deleteResponsableSiege(@PathVariable("id") Long id)
	  {
		  try
		  {
			  ResponsableSiege responsablesiege= responsablesiegeRepository.findById(id).get();

			  responsablesiege.setDateOp(LocalDateTime.now());
			  responsablesiege.setUtil("admin");
			  responsablesiege.setOp("D"); // D,E
			  responsablesiegeRepository.delete(responsablesiege);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}

