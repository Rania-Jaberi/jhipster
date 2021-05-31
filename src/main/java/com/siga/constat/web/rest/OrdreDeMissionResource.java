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

import com.siga.constat.domain.OrdreDeMission;
import com.siga.constat.domain.repository.OrdreDeMissionRepository;


@RestController
@RequestMapping("/api")
public class OrdreDeMissionResource {

	
	@Autowired
	private OrdreDeMissionRepository ordredemissionRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/ordredemission")
	  public OrdreDeMission addOrdreDeMission(@RequestBody OrdreDeMission ordredemission)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  ordredemission.setDateOp(LocalDateTime.now());
			  ordredemission.setUtil("admin");
			  ordredemission.setOp("A"); // D,E
			  ordredemission= ordredemissionRepository.save(ordredemission);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return ordredemission;
	  }
	  
	  
	  
	  @PutMapping("/ordredemission")
	  public OrdreDeMission editOrdreDeMission(@RequestBody OrdreDeMission ordredemission)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  ordredemission.setDateOp(LocalDateTime.now());
			  ordredemission.setUtil("admin");
			  ordredemission.setOp("E"); // D,E
			  ordredemission= ordredemissionRepository.save(ordredemission);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return ordredemission;
	  }
	  
	  
	  
	  @GetMapping("/ordredemission/{id}")
	  public OrdreDeMission getOrdreDeMission(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return ordredemissionRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/ordredemission")
	  public List<OrdreDeMission> getAllOrdreDeMissions()
	  {
		  try
		  {
			  return ordredemissionRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/ordredemission/{id}")
	  public boolean deleteOrdreDeMission(@PathVariable("id") Long id)
	  {
		  try
		  {
			  OrdreDeMission ordredemission= ordredemissionRepository.findById(id).get();

			  ordredemission.setDateOp(LocalDateTime.now());
			  ordredemission.setUtil("admin");
			  ordredemission.setOp("D"); // D,E
			  ordredemissionRepository.delete(ordredemission);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
