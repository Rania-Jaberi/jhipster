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

import com.siga.constat.domain.IndemnisationVictime;
import com.siga.constat.domain.repository.IndemnisationVictimeRepository;



@RestController
@RequestMapping("/api")
public class IndemnisationVictimeResource {

	
	@Autowired
	private IndemnisationVictimeRepository indemnisationvictimeRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/indemnisationvictime")
	  public IndemnisationVictime addIndemnisationVictime(@RequestBody IndemnisationVictime indemnisationvictime)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  indemnisationvictime.setDateOp(LocalDateTime.now());
			  indemnisationvictime.setUtil("admin");
			  indemnisationvictime.setOp("A"); // D,E
			  indemnisationvictime= indemnisationvictimeRepository.save(indemnisationvictime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return indemnisationvictime;
	  }
	  
	  
	  
	  @PutMapping("/indemnisationvictime")
	  public IndemnisationVictime editIndemnisationVictime(@RequestBody IndemnisationVictime indemnisationvictime)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  indemnisationvictime.setDateOp(LocalDateTime.now());
			  indemnisationvictime.setUtil("admin");
			  indemnisationvictime.setOp("E"); // D,E
			  indemnisationvictime= indemnisationvictimeRepository.save(indemnisationvictime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return indemnisationvictime;
	  }
	  
	  
	  
	  @GetMapping("/indemnisationvictime/{id}")
	  public IndemnisationVictime getIndemnisationVictime(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return indemnisationvictimeRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/indemnisationvictime")
	  public List<IndemnisationVictime> getAllIndemnisationVictimes()
	  {
		  try
		  {
			  return indemnisationvictimeRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/indemnisationvictime/{id}")
	  public boolean deleteIndemnisationVictime(@PathVariable("id") Long id)
	  {
		  try
		  {
			  IndemnisationVictime indemnisationvictime= indemnisationvictimeRepository.findById(id).get();

			  indemnisationvictime.setDateOp(LocalDateTime.now());
			  indemnisationvictime.setUtil("admin");
			  indemnisationvictime.setOp("D"); // D,E
			  indemnisationvictimeRepository.delete(indemnisationvictime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
