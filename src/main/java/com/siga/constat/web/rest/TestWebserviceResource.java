package com.siga.constat.domain.resource;/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siga.constat.domain.Pmorale;
import com.siga.constat.domain.Pphysique;
import com.siga.constat.domain.repository.AssureeRepository;

@RestController // cette class est une class qui contient des fonctions qui exposent des webservice en mode REST
public class TestWebserviceResource {

	
	
	
	/*
	@Autowired
	private AssureeRepository assureeRepository;
	
	
	
	@PostMapping("/save-ph")
	public void savePH(@RequestBody Pphysique obj)
	{
		assureeRepository.save(obj);
	}
	
	
	
	@PostMapping("/save-pm")
	public void savePM(@RequestBody Pmorale obj)
	{
		assureeRepository.save(obj);
	}
	
	
	
	
//
//private static final int Password = 0;
//@Autowired // injection class et interface
//private UserRepository repo; // repository qui contient la liste des fonctions prédéf (query JDBC )
//
//
//
//
//@RequestMapping(path="/test-web-body",method=RequestMethod.POST)
//public String testWebserviceRequestParam(@RequestBody User user )  // fonction testwebservice : websevice REST en mode GET
//{
//	
//	/*
//	 * String createBy (java) created_by
//	 * 
//	 * set partie java : valeur : system
//	 *  traitement set date > 6 =1
//	 *                      <6 = 0
//	 * */
//	
//	user.setcreateBy("system");
//	
//  if (      user.getPassword().length()	>6)
//  {
//	  user.setCheck(1);
//  }
//  else
//	  user.setCheck(0);
//	
//user.setDateCreation(LocalDateTime.now());
//
//repo.save(user); // insert into constat_user (login,password) values (user.get ) ;
//System.out.print("user login : " + user.getLogin());
//return "user found";
//}
//
//
//
//@GetMapping("/tesst")
//public List<User> getall()
//{
//	return repo.findAll();
//}

