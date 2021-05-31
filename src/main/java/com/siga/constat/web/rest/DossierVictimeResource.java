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

import com.siga.constat.domain.DossierVictime;
import com.siga.constat.domain.repository.DossierVictimeRepository;


@RestController
@RequestMapping("/api")
public class DossierVictimeResource {

	
	@Autowired
	private DossierVictimeRepository dossiervictimeRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/dossiervictime")
	  public DossierVictime addDossierVictime(@RequestBody DossierVictime dossiervictime)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  dossiervictime.setDateOp(LocalDateTime.now());
			  dossiervictime.setUtil("admin");
			  dossiervictime.setOp("A"); // D,E
			  dossiervictime= dossiervictimeRepository.save(dossiervictime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return dossiervictime;
	  }
	  
	  
	  
	  @PutMapping("/dossiervictime")
	  public DossierVictime editDossierVictime(@RequestBody DossierVictime dossiervictime)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  dossiervictime.setDateOp(LocalDateTime.now());
			  dossiervictime.setUtil("admin");
			  dossiervictime.setOp("E"); // D,E
			  dossiervictime= dossiervictimeRepository.save(dossiervictime);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return dossiervictime;
	  }
	  
	  
	  
	  @GetMapping("/dossiervictime/{id}")
	  public DossierVictime getDossierVictime(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return dossiervictimeRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/dossiervictime")
	  public List<DossierVictime> getAllDossierVictimes()
	  {
		  try
		  {
			  return dossiervictimeRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/dossiervictime/{id}")
	  public boolean deleteDossierVictime(@PathVariable("id") Long id)
	  {
		  try
		  {
			  DossierVictime dossiervictime= dossiervictimeRepository.findById(id).get();

			  dossiervictime.setDateOp(LocalDateTime.now());
			  dossiervictime.setUtil("admin");
			  dossiervictime.setOp("D"); // D,E
			  dossiervictimeRepository.delete(dossiervictime);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
