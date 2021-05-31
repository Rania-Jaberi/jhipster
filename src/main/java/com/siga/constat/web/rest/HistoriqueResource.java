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

import com.siga.constat.domain.Historique;
import com.siga.constat.domain.repository.HistoriqueRepository;


@RestController
@RequestMapping("/api")
public class HistoriqueResource {

	
	@Autowired
	private HistoriqueRepository historiqueRepository;
	
	
	/*
	 *  les webservice : ajout/consultation/modification/suppression
	 *    CRUD : 
	 *    
	 *    
	 *    
	 * */
	
		
	  @PostMapping("/historique")
	  public Historique addHistorique(@RequestBody Historique historique)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  historique.setDateOp(LocalDateTime.now());
			  historique.setUtil("admin");
			  historique.setOp("A"); // D,E
			  historique= historiqueRepository.save(historique);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return historique;
	  }
	  
	  
	  
	  @PutMapping("/historique")
	  public Historique editHistorique(@RequestBody Historique historique)
	  {
		  try
		  {
		    
			  /* ajout traitement spécial :
			   * setDateCreate setCreatedBy
			   * */
			  historique.setDateOp(LocalDateTime.now());
			  historique.setUtil("admin");
			  historique.setOp("E"); // D,E
			  historique= historiqueRepository.save(historique);
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return historique;
	  }
	  
	  
	  
	  @GetMapping("/historique/{id}")
	  public Historique getHistorique(@PathVariable("id") Long id)
	  {
		  try
		  {
			  return historiqueRepository.findById(id).get();
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
		  
	  
	  @GetMapping("/historique")
	  public List<Historique> getAllHistoriques()
	  {
		  try
		  {
			  return historiqueRepository.findAll(); // SELECT * FROM agence
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return null;
	  }
	  
	  
	
	  
	  
	  
	  
	  @DeleteMapping("/historique/{id}")
	  public boolean deleteHistorique(@PathVariable("id") Long id)
	  {
		  try
		  {
			  Historique historique= historiqueRepository.findById(id).get();

			  historique.setDateOp(LocalDateTime.now());
			  historique.setUtil("admin");
			  historique.setOp("D"); // D,E
			  historiqueRepository.delete(historique);
			  return true;
		  }
		  catch(Exception e)
		  {
			  
		  }
		  return false;
	  }
		  
	  
	  
	  
	  
	  
}
