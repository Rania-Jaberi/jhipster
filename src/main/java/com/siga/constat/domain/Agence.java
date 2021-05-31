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
@Table(name="Agence")

public class Agence implements Serializable{
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  num ;
	
	@Column(name="nom")
	private String  nom ;
	
	@Column(name="adresse")
	private String  adresse ;
	
	
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

	public Assurance getAssurance() {
		return assurance;
	}

	public void setAssurance(Assurance assurance) {
		this.assurance = assurance;
	}

	public Set<Dossier> getDossiers() {
		return dossiers;
	}

	public void setDossiers(Set<Dossier> dossiers) {
		this.dossiers = dossiers;
	}

	public Set<Assure> getAssures() {
		return assures;
	}

	public void setAssures(Set<Assure> assures) {
		this.assures = assures;
	}

	public Set<Gestionnaire> getGestionnaires() {
		return gestionnaires;
	}

	public void setGestionnaires(Set<Gestionnaire> gestionnaires) {
		this.gestionnaires = gestionnaires;
	}

	public Set<Demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(Set<Demande> demandes) {
		this.demandes = demandes;
	}

	public Set<Historique> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(Set<Historique> historiques) {
		this.historiques = historiques;
	}

	public Comptable getComptable() {
		return comptable;
	}

	public void setComptable(Comptable comptable) {
		this.comptable = comptable;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	@ManyToOne
    @JoinColumn(name="id_assurance")
    private Assurance assurance;
	@OneToMany(mappedBy="agence")
	@JsonIgnore
	private Set<Dossier> dossiers;
	
	@OneToMany(mappedBy="agence")
	@JsonIgnore
	private Set<Assure> assures;
	
	@OneToMany(mappedBy="agence")
	@JsonIgnore
	private Set<Gestionnaire> gestionnaires;
	
	@OneToMany(mappedBy="agence")
	@JsonIgnore
	private Set<Demande> demandes ;
	
	@OneToMany(mappedBy="agence")
	@JsonIgnore
	private Set<Historique> historiques ;
	@ManyToOne
	 @JoinColumn(name="comptable")
	   private Comptable comptable ;
	

}
