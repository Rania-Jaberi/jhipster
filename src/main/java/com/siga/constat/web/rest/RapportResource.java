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

import com.siga.constat.domain.Rapport;
import com.siga.constat.domain.repository.RapportRepository;



@RestController
@RequestMapping("/api")
public class RapportResource {

	
	@Autowired
	private RapportRepository rapportRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/rapport")
	  public Rapport addRapport(@RequestBody Rapport rapport)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  rapport.setDateOp(LocalDateTime.now());
			  rapport.setUtil("admin");
			  rapport.setOp("A"); // D,E
			  rapport= rapportRepository.save(rapport);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return rapport;
	  }
	  
	  
	  
	  @PutMapping("/rapport")
	  public Rapport editRapport(@RequestBody Rapport rapport)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  rapport.setDateOp(LocalDateTime.now());
			  rapport.setUtil("admin");
			  rapport.setOp("E"); // D,E
			  rapport= rapportRepository.save(rapport);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return rapport;
	  }
	  
	  
	  
	  @GetMapping("/rapport/{id}")
	  public Rapport getRapport(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return rapportRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/rapport")
	  public List<Rapport> getAllRapports()
	  {
		  try
		  {
			  return rapportRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/rapport/{id}")
	  public boolean deleteRapport(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Rapport rapport= rapportRepository.findById(id).get();

			  rapport.setDateOp(LocalDateTime.now());
			  rapport.setUtil("admin");
			  rapport.setOp("D"); // D,E
			  rapportRepository.delete(rapport);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
