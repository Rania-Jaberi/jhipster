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

import com.siga.constat.domain.VictimeDemande;
import com.siga.constat.domain.repository.VictimeDemandeRepository;



@RestController
@RequestMapping("/api")
public class VictimeDemandeResource {

	
	@Autowired
	private VictimeDemandeRepository victimedemandeRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/victimedemande")
	  public VictimeDemande addVictimeDemande(@RequestBody VictimeDemande victimedemande)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  victimedemande.setDateOp(LocalDateTime.now());
			  victimedemande.setUtil("admin");
			  victimedemande.setOp("A"); // D,E
			  victimedemande= victimedemandeRepository.save(victimedemande);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return victimedemande;
	  }
	  
	  
	  
	  @PutMapping("/victimedemande")
	  public VictimeDemande editVictimeDemande(@RequestBody VictimeDemande victimedemande)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  victimedemande.setDateOp(LocalDateTime.now());
			  victimedemande.setUtil("admin");
			  victimedemande.setOp("E"); // D,E
			  victimedemande= victimedemandeRepository.save(victimedemande);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return victimedemande;
	  }
	  
	  
	  
	  @GetMapping("/victimedemande/{id}")
	  public VictimeDemande getVictimeDemande(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return victimedemandeRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/victimedemande")
	  public List<VictimeDemande> getAllVictimeDemandes()
	  {
		  try
		  {
			  return victimedemandeRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/victimedemande/{id}")
	  public boolean deleteVictimeDemande(@PathVariable("id") Long id)
	  {
		  try
		  {
			  VictimeDemande victimedemande= victimedemandeRepository.findById(id).get();

			  victimedemande.setDateOp(LocalDateTime.now());
			  victimedemande.setUtil("admin");
			  victimedemande.setOp("D"); // D,E
			  victimedemandeRepository.delete(victimedemande);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}

