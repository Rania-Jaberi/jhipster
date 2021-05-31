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

import com.siga.constat.domain.ExpertSinistre;
import com.siga.constat.domain.repository.ExpertSinistreRepository;


@RestController
@RequestMapping("/api")
public class ExpertSinistreResource {

	
	@Autowired
	private ExpertSinistreRepository expertsinistreRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/expertsinistre")
	  public ExpertSinistre addExpertSinistre(@RequestBody ExpertSinistre expertsinistre)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  expertsinistre.setDateOp(LocalDateTime.now());
			  expertsinistre.setUtil("admin");
			  expertsinistre.setOp("A"); // D,E
			  expertsinistre= expertsinistreRepository.save(expertsinistre);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return expertsinistre;
	  }
	  
	  
	  
	  @PutMapping("/expertsinistre")
	  public ExpertSinistre editExpertSinistre(@RequestBody ExpertSinistre expertsinistre)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  expertsinistre.setDateOp(LocalDateTime.now());
			  expertsinistre.setUtil("admin");
			  expertsinistre.setOp("E"); // D,E
			  expertsinistre= expertsinistreRepository.save(expertsinistre);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return expertsinistre;
	  }
	  
	  
	  
	  @GetMapping("/expertsinistre/{id}")
	  public ExpertSinistre getExpertSinistre(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return expertsinistreRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/expertsinistre")
	  public List<ExpertSinistre> getAllExpertSinistres()
	  {
		  try
		  {
			  return expertsinistreRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/expertsinistre/{id}")
	  public boolean deleteExpertSinistre(@PathVariable("id") Long id)
	  {
		  try
		  {
			  ExpertSinistre expertsinistre= expertsinistreRepository.findById(id).get();

			  expertsinistre.setDateOp(LocalDateTime.now());
			  expertsinistre.setUtil("admin");
			  expertsinistre.setOp("D"); // D,E
			  expertsinistreRepository.delete(expertsinistre);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
