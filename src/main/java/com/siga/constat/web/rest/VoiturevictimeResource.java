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

import com.siga.constat.domain.VoitureVictime;
import com.siga.constat.domain.repository.VoiturevictimeRepository;



@RestController
@RequestMapping("/api")
public class VoiturevictimeResource {

	
	@Autowired
	private VoiturevictimeRepository voiturevictimeRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/voiturevictime")
	  public VoitureVictime addVoiturevictime(@RequestBody VoitureVictime voiturevictime)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  voiturevictime.setDateOp(LocalDateTime.now());
			  voiturevictime.setUtil("admin");
			  voiturevictime.setOp("A"); // D,E
			  voiturevictime= voiturevictimeRepository.save(voiturevictime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return voiturevictime;
	  }
	  
	  
	  
	  @PutMapping("/voiturevictime")
	  public VoitureVictime editAgence(@RequestBody VoitureVictime voiturevictime)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  voiturevictime.setDateOp(LocalDateTime.now());
			  voiturevictime.setUtil("admin");
			  voiturevictime.setOp("E"); // D,E
			  voiturevictime= voiturevictimeRepository.save(voiturevictime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return voiturevictime;
	  }
	  
	  
	  
	  @GetMapping("/voiturevictime/{id}")
	  public VoitureVictime getVoitureVictime(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return voiturevictimeRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/voiturevictime")
	  public List<VoitureVictime> getAllVoitureVictimes()
	  {
		  try
		  {
			  return voiturevictimeRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/voiturevictime/{id}")
	  public boolean deleteVoitureVictime(@PathVariable("id") Long id)
	  {
		  try
		  {
			  VoitureVictime voiturevictime= voiturevictimeRepository.findById(id).get();

			  voiturevictime.setDateOp(LocalDateTime.now());
			  voiturevictime.setUtil("admin");
			  voiturevictime.setOp("D"); // D,E
			  voiturevictimeRepository.delete(voiturevictime);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}


