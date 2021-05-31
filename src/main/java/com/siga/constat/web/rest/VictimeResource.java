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

import com.siga.constat.domain.Victime;
import com.siga.constat.domain.repository.VictimeRepository;



@RestController
@RequestMapping("/api")
public class VictimeResource {

	
	@Autowired
	private VictimeRepository victimeRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/victime")
	  public Victime addVictime(@RequestBody Victime victime)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  victime.setDateOp(LocalDateTime.now());
			  victime.setUtil("admin");
			  victime.setOp("A"); // D,E
			  victime= victimeRepository.save(victime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return victime;
	  }
	  
	  
	  
	  @PutMapping("/victime")
	  public Victime editvictime(@RequestBody Victime victime)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  victime.setDateOp(LocalDateTime.now());
			  victime.setUtil("admin");
			  victime.setOp("E"); // D,E
			  victime= victimeRepository.save(victime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return victime;
	  }
	  
	  
	  
	  @GetMapping("/victime/{id}")
	  public Victime getVictime(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return victimeRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/victime")
	  public List<Victime> getAllVictimes()
	  {
		  try
		  {
			  return victimeRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/victime/{id}")
	  public boolean deleteVictime(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Victime victime= victimeRepository.findById(id).get();

			  victime.setDateOp(LocalDateTime.now());
			  victime.setUtil("admin");
			  victime.setOp("D"); // D,E
			  victimeRepository.delete(victime);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
