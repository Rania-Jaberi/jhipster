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

import com.siga.constat.domain.Voiture;
import com.siga.constat.domain.repository.VoitureRepository;



@RestController
@RequestMapping("/api")
public class VoitureResource {

	
	@Autowired
	private VoitureRepository voitureRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/voiture")
	  public Voiture addVoiture(@RequestBody Voiture voiture)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  voiture.setDateOp(LocalDateTime.now());
			  voiture.setUtil("admin");
			  voiture.setOp("A"); // D,E
			  voiture= voitureRepository.save(voiture);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return voiture;
	  }
	  
	  
	  
	  @PutMapping("/voiture")
	  public Voiture editVoiture(@RequestBody Voiture voiture)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  voiture.setDateOp(LocalDateTime.now());
			  voiture.setUtil("admin");
			  voiture.setOp("E"); // D,E
			  voiture= voitureRepository.save(voiture);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return voiture;
	  }
	  
	  
	  
	  @GetMapping("/voiture/{id}")
	  public Voiture getvoiture(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return voitureRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/voiture")
	  public List<Voiture> getAllVoitures()
	  {
		  try
		  {
			  return voitureRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/voiture/{id}")
	  public boolean deleteVoiture(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Voiture voiture= voitureRepository.findById(id).get();

			  voiture.setDateOp(LocalDateTime.now());
			  voiture.setUtil("admin");
			  voiture.setOp("D"); // D,E
			  voitureRepository.delete(voiture);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}



