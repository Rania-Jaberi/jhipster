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

import com.siga.constat.domain.RapportVictime;
import com.siga.constat.domain.repository.RapportVictimeRepository;


@RestController
@RequestMapping("/api")
public class RapportVictimeResource {

	
	@Autowired
	private RapportVictimeRepository rapportvictimeRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/rapportvictime")
	  public RapportVictime addRapportVictime(@RequestBody RapportVictime rapportvictime)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  rapportvictime.setDateOp(LocalDateTime.now());
			  rapportvictime.setUtil("admin");
			  rapportvictime.setOp("A"); // D,E
			  rapportvictime= rapportvictimeRepository.save(rapportvictime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return rapportvictime;
	  }
	  
	  
	  
	  @PutMapping("/rapportvictime")
	  public RapportVictime editRapportVictime(@RequestBody RapportVictime rapportvictime)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  rapportvictime.setDateOp(LocalDateTime.now());
			  rapportvictime.setUtil("admin");
			  rapportvictime.setOp("E"); // D,E
			  rapportvictime= rapportvictimeRepository.save(rapportvictime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return rapportvictime;
	  }
	  
	  
	  
	  @GetMapping("/rapportvictime/{id}")
	  public RapportVictime getRapportVictime(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return rapportvictimeRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/rapportvictime")
	  public List<RapportVictime> getAllRapportVictimes()
	  {
		  try
		  {
			  return rapportvictimeRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/rapportvictime/{id}")
	  public boolean deleteRapportVictime(@PathVariable("id") Long id)
	  {
		  try
		  {
			  RapportVictime rapportvictime= rapportvictimeRepository.findById(id).get();

			  rapportvictime.setDateOp(LocalDateTime.now());
			  rapportvictime.setUtil("admin");
			  rapportvictime.setOp("D"); // D,E
			  rapportvictimeRepository.delete(rapportvictime);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}

