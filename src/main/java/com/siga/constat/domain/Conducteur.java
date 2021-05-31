package com.siga.constat.domain;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name="conducteur")
public class Conducteur implements Serializable{
	@OneToMany(mappedBy="conducteur")
	@JsonIgnore
	private Set<Victime> victimes;
	
	@OneToMany(mappedBy="conducteur")
	@JsonIgnore
	private Set<DossierConducteur> dossierconducteurs;
	
	@OneToMany(mappedBy="conducteur")
	@JsonIgnore
	private Set<ConducteurRapport> Conducteurrapports;
	
	@OneToMany(mappedBy="conducteur")
	@JsonIgnore
	private Set<ConducteurVoiture> conducteurvoitures;
	
	@OneToMany(mappedBy="conducteur")
	@JsonIgnore
	private Set<DemandeConducteur> demandeconducteurs ;
	
	@ManyToOne
    @JoinColumn(name="id_gestionnaires")
    private  Gestionnaire gestionnaire;
	 @ManyToOne
	    @JoinColumn(name="id_historique")
	    private  Historique historique;
	 
	 
	
	
		



@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

@Column(name="nom")
private String nom;

@Column(name="Prenom")
private String prenom;

@Column(name="date_naissance")
private LocalDate  DN ;

@Column(name="adresse")
private String adresse;

/*history */
@Column(name="date_op")
private LocalDateTime dateOp;

@Column(name="op")
private String op ;

@Column(name="util")
private String util;

public Set<Victime> getVictimes() {
	return victimes;
}

public void setVictimes(Set<Victime> victimes) {
	this.victimes = victimes;
}

public Set<DossierConducteur> getDossierconducteurs() {
	return dossierconducteurs;
}

public void setDossierconducteurs(Set<DossierConducteur> dossierconducteurs) {
	this.dossierconducteurs = dossierconducteurs;
}

public Set<ConducteurRapport> getConducteurrapports() {
	return Conducteurrapports;
}

public void setConducteurrapports(Set<ConducteurRapport> conducteurrapports) {
	Conducteurrapports = conducteurrapports;
}

public Set<ConducteurVoiture> getConducteurvoitures() {
	return conducteurvoitures;
}

public void setConducteurvoitures(Set<ConducteurVoiture> conducteurvoitures) {
	this.conducteurvoitures = conducteurvoitures;
}

public Set<DemandeConducteur> getDemandeconducteurs() {
	return demandeconducteurs;
}

public void setDemandeconducteurs(Set<DemandeConducteur> demandeconducteurs) {
	this.demandeconducteurs = demandeconducteurs;
}

public Gestionnaire getGestionnaire() {
	return gestionnaire;
}

public void setGestionnaire(Gestionnaire gestionnaire) {
	this.gestionnaire = gestionnaire;
}

public Historique getHistorique() {
	return historique;
}

public void setHistorique(Historique historique) {
	this.historique = historique;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
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

public String getPrenom() {
	return prenom;
}

@Column(name="numero_permis")
private Integer numero_permis;

@Column(name="email")
private String email;


@Column(name="delivre")
private LocalDate delivre;



public String getnom() {
return nom;
}

public void setnom(String nom) {
this.nom = nom;
}

public String getprenom() {
return prenom;
}

public void setPrenom(String prenom) {
this. prenom= prenom;
}

public LocalDate getDN() {
return DN;
}

public void setDN(LocalDate dN) {
DN = dN;
}


public   String getAdresse() {
return adresse;
}

public void setAdresse( String adresse) {
this.adresse = adresse;
}


public Integer getNumero_permis() {
return numero_permis;
}

public void setNumero_permis(Integer numero_permis) {
this.numero_permis = numero_permis;
}



public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public LocalDate getDelivre() {
return delivre;
}

public void setDelivre(LocalDate delivre) {
this.delivre = delivre;
}




}
