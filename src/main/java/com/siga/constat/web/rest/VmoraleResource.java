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

import com.siga.constat.domain.Agence;
import com.siga.constat.domain.Vmorale;
import com.siga.constat.domain.repository.AgenceRepository;
import com.siga.constat.domain.repository.VmoraleRepository;



@RestController
@RequestMapping("/api")
public class VmoraleResource {

	
	@Autowired
	private VmoraleRepository vmoraleRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/vmorale")
	  public Vmorale addVmorale(@RequestBody Vmorale vmorale)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  vmorale.setDateOp(LocalDateTime.now());
			  vmorale.setUtil("admin");
			  vmorale.setOp("A"); // D,E
			  vmorale= vmoraleRepository.save(vmorale);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return vmorale;
	  }
	  
	  
	  
	  @PutMapping("/vmorale")
	  public Vmorale editVmorale(@RequestBody Vmorale vmorale)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  vmorale.setDateOp(LocalDateTime.now());
			  vmorale.setUtil("admin");
			  vmorale.setOp("E"); // D,E
			  vmorale= vmoraleRepository.save(vmorale);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return vmorale;
	  }
	  
	  
	  
	  @GetMapping("/vmorale/{id}")
	  public Vmorale getVmorale(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return vmoraleRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/vmorale")
	  public List<Vmorale> getAllVmorales()
	  {
		  try
		  {
			  return vmoraleRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/vmorale/{id}")
	  public boolean deleteVmorale(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Vmorale vmorale= vmoraleRepository.findById(id).get();

			  vmorale.setDateOp(LocalDateTime.now());
			  vmorale.setUtil("admin");
			  vmorale.setOp("D"); // D,E
			  vmoraleRepository.delete(vmorale);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
  
	


