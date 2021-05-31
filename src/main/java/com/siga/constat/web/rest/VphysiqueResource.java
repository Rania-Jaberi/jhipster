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

import com.siga.constat.domain.Vphysique;
import com.siga.constat.domain.repository.VphysiqueRepository;



@RestController
@RequestMapping("/api")
public class VphysiqueResource {

	
	@Autowired
	private VphysiqueRepository vphysiqueRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/vphysique")
	  public Vphysique addVphysique(@RequestBody Vphysique vphysique)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  vphysique.setDateOp(LocalDateTime.now());
			  vphysique.setUtil("admin");
			  vphysique.setOp("A"); // D,E
			  vphysique= vphysiqueRepository.save(vphysique);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return vphysique;
	  }
	  
	  
	  
	  @PutMapping("/vphysique")
	  public Vphysique editVphysique(@RequestBody Vphysique vphysique)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  vphysique.setDateOp(LocalDateTime.now());
			  vphysique.setUtil("admin");
			  vphysique.setOp("E"); // D,E
			  vphysique= vphysiqueRepository.save(vphysique);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return vphysique;
	  }
	  
	  
	  
	  @GetMapping("/vphysique/{id}")
	  public Vphysique getVphysique(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return vphysiqueRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/vphysique")
	  public List<Vphysique> getAllVphysiques()
	  {
		  try
		  {
			  return vphysiqueRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/vphysique/{id}")
	  public boolean deleteVphysique(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Vphysique vphysique= vphysiqueRepository.findById(id).get();

			  vphysique.setDateOp(LocalDateTime.now());
			  vphysique.setUtil("admin");
			  vphysique.setOp("D"); // D,E
			  vphysiqueRepository.delete(vphysique);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}


