package com.siga.constat.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_juridique")

@Table(name="assure")
public  abstract class Assure implements Serializable{


@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long ID;

	public Long getID() {
	return ID;
}

public void setID(Long iD) {
	ID = iD;
}

/*history */
@Column(name="date_op")
private LocalDateTime dateOp;

@Column(name="op")
private String op ;

@Column(name="util")
private String util;

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

public Set<AssureIndemnisation> getAssureindemnisations() {
	return assureindemnisations;
}

public void setAssureindemnisations(Set<AssureIndemnisation> assureindemnisations) {
	this.assureindemnisations = assureindemnisations;
}

public Set<AssureDossier> getAssuredossiers() {
	return assuredossiers;
}

public void setAssuredossiers(Set<AssureDossier> assuredossiers) {
	this.assuredossiers = assuredossiers;
}





public Set<Voiture> getVoitures() {
	return voitures;
}

public void setVoitures(Set<Voiture> voitures) {
	this.voitures = voitures;
}

public Set<Demande> getDemandes() {
	return demandes;
}

public void setDemandes(Set<Demande> demandes) {
	this.demandes = demandes;
}

public Agence getAgence() {
	return agence;
}

public void setAgence(Agence agence) {
	this.agence = agence;
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

	@OneToMany(mappedBy="assure")
	@JsonIgnore
	private Set<AssureIndemnisation> assureindemnisations;
	
	@OneToMany(mappedBy="assure")
	@JsonIgnore
	private Set<AssureDossier> assuredossiers;
	
	@OneToMany(mappedBy="assure")
	@JsonIgnore
	private Set<AssureRapport> assurerapports;
	
	public Set<AssureRapport> getAssurerapports() {
		return assurerapports;
	}

	public void setAssurerapports(Set<AssureRapport> assurerapports) {
		this.assurerapports = assurerapports;
	}

	@OneToMany(mappedBy="assure")
	@JsonIgnore
	private Set<AssureVictime> AssureVictimes ;
	
	public Set<AssureVictime> getAssureVictimes() {
		return AssureVictimes;
	}

	public void setAssureVictimes(Set<AssureVictime> assureVictimes) {
		AssureVictimes = assureVictimes;
	}

	@OneToMany(mappedBy="assure")
	@JsonIgnore
	private Set<Voiture> voitures;

	
	@OneToMany(mappedBy="assure")
	@JsonIgnore
	private Set<Demande> demandes ;
	
	
	 
	 
	@ManyToOne
	@JoinColumn(name="id_agence")
    private Agence  agence  ;
	
	
	@ManyToOne
	@JoinColumn(name="id_gestionnaire ")
    private Gestionnaire  gestionnaire  ;
	
	
	 

	@ManyToOne
	@JoinColumn(name="id_historiques")
    private Historique  historique ;

}