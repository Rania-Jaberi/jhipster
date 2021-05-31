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

import com.siga.constat.domain.Pphysique;
import com.siga.constat.domain.repository.PphysiqueRepository;



@RestController
@RequestMapping("/api")
public class PphysiqueResource {

	
	@Autowired
	private PphysiqueRepository pphysiqueRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/pphysique")
	  public Pphysique addPphysique(@RequestBody Pphysique pphysique)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  pphysique.setDateOp(LocalDateTime.now());
			  pphysique.setUtil("admin");
			  pphysique.setOp("A"); // D,E
			  pphysique= pphysiqueRepository.save(pphysique);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return pphysique;
	  }
	  
	  
	  
	  @PutMapping("/pphysique")
	  public Pphysique editPphysique(@RequestBody Pphysique pphysique)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  pphysique.setDateOp(LocalDateTime.now());
			  pphysique.setUtil("admin");
			  pphysique.setOp("E"); // D,E
			  pphysique= pphysiqueRepository.save(pphysique);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return pphysique;
	  }
	  
	  
	  
	  @GetMapping("/pphysique/{id}")
	  public Pphysique getPphysique(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return pphysiqueRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/pphysique")
	  public List<Pphysique> getAllPphysiques()
	  {
		  try
		  {
			  return pphysiqueRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/pphysique/{id}")
	  public boolean deletePphysique(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Pphysique pphysique= pphysiqueRepository.findById(id).get();

			  pphysique.setDateOp(LocalDateTime.now());
			  pphysique.setUtil("admin");
			  pphysique.setOp("D"); // D,E
			  pphysiqueRepository.delete(pphysique);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
