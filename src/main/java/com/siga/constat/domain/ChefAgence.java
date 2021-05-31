package com.siga.constat.domain;



import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name="Chef_agence")
public class ChefAgence implements Serializable{
	
	@OneToMany(mappedBy="chefAgence")
	@JsonIgnore
	private Set<Indemnisation> indemnisations;
	 @ManyToOne
	    @JoinColumn(name="id_assurance")
	    private Assurance  assurance ;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long Cin;

/*history */
@Column(name="date_op")
private LocalDateTime dateOp;

@Column(name="op")
private String op ;

@Column(name="util")
private String util;

public Set<Indemnisation> getIndemnisations() {
	return indemnisations;
}

public void setIndemnisations(Set<Indemnisation> indemnisations) {
	this.indemnisations = indemnisations;
}

public Assurance getAssurance() {
	return assurance;
}

public void setAssurance(Assurance assurance) {
	this.assurance = assurance;
}

public LocalDateTime getDateOp() {
	return dateOp;
}

public void setDateOp(LocalDateTime dateOp) {
	this.dateOp = dateOp;
}

public String getOp() {
	return op;
}

public void setOp(String op) {
	this.op = op;
}

public String getUtil() {
	return util;
}

public void setUtil(String util) {
	this.util = util;
}

@Column(name="nom")
private String nom;

@Column(name="prenom")
private String prenom;

@Column(name="mail")
private String mail;

@Column(name="num_tel")
private Long num_tel;

public Long getCin() {
return Cin;
}

public void setCin(Long cin) {
Cin = cin;
}

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

public String getMail() {
return mail;
}

public void setMail(String mail) {
this.mail = mail;
}

public Long getNum_tel() {
return num_tel;
}

public void setNum_tel(Long num_tel) {
this.num_tel = num_tel;
}




}
