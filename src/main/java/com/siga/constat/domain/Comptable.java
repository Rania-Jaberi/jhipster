package com.siga.constat.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name="Comptable")
public class Comptable implements Serializable{
	
	@OneToMany(mappedBy="comptable")
	@JsonIgnore
	private Set<Rapport> rapports;
	
	@OneToMany(mappedBy="comptable")
	@JsonIgnore
	private Set<Agence> agences;
	
	@OneToMany(mappedBy="comptable")
	@JsonIgnore
	private Set<Dossier> dossiers;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long num;

/*history */
@Column(name="date_op")
private LocalDateTime dateOp;

@Column(name="op")
private String op ;

@Column(name="util")
private String util;

public Set<Rapport> getRapports() {
	return rapports;
}

public void setRapports(Set<Rapport> rapports) {
	this.rapports = rapports;
}

public Set<Agence> getAgences() {
	return agences;
}

public void setAgences(Set<Agence> agences) {
	this.agences = agences;
}

public Set<Dossier> getDossiers() {
	return dossiers;
}

public void setDossiers(Set<Dossier> dossiers) {
	this.dossiers = dossiers;
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

@Column(name="cin")
private String cin;

@Column(name="nom")
private String nom;

@Column(name="prenom")
private String prenom;

@Column(name="mail")
private String mail;

@Column(name="adresse")
private String adresse;

@Column(name="num_tel")
private Long num_tel;

public Long getNum() {
return num;
}

public void setNum(Long num) {
this.num = num;
}

public String getCin() {
return cin;
}

public void setCin(String cin) {
this.cin = cin;
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

public String getAdresse() {
return adresse;
}

public void setAdresse(String adresse) {
this.adresse = adresse;
}

public Long getNum_tel() {
return num_tel;
}

public void setNum_tel(Long num_tel) {
this.num_tel = num_tel;
}



}
