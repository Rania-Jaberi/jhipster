package com.siga.constat.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings({ "serial", "unused" })
@Entity
@DiscriminatorValue("Vp")
public class Vphysique   extends Victime{



@Column(name="nom_victime")
private String nom;


@Column(name="Prenom_victime")
private String prenom;

@Column(name="date_naissance")
private String  DN ;

/*history */
@Column(name="date_op")
private LocalDateTime dateOp;

@Column(name="op")
private String op ;

@Column(name="util")
private String util;

    public String getOp() {
	return op;
}




public String getUtil() {
	return util;
}


public void setUtil(String util) {
	this.util = util;
}


public LocalDateTime getDateOp() {
	return dateOp;
}


	@Column(name="email")
    private String email;


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getDN() {
		return DN;
	}


	public void setDN(String dN) {
		DN = dN;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	

    


}
