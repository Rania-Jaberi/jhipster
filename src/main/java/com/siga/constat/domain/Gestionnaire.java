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

@SuppressWarnings({ "serial", "unused" })
@Entity
@Table(name="Gestionnaire")
public class Gestionnaire implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  id ;
	
	@Column(name="Nom")
	private String  nom ;
	
	@Column(name="Prenom")
	private String  prenom ;
	
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	
	@OneToMany(mappedBy="gestionnaire")
	@JsonIgnore
	private Set<Demande> demandes;
	
	@OneToMany(mappedBy="gestionnaire")
	@JsonIgnore
	private Set<Assure> assures;
	
	@OneToMany(mappedBy="gestionnaire")
	@JsonIgnore
	private Set<Conducteur> conducteurs;
	
	@OneToMany(mappedBy="gestionnaire")
	@JsonIgnore
	private Set< Voiture> voitures;
	
	@OneToMany(mappedBy="gestionnaire")
	@JsonIgnore
	private Set<Victime> victimes;
	
	 @ManyToOne
	    @JoinColumn(name="id_assurance")
	    private Assurance  assurance ;
	 
	 @ManyToOne
	    @JoinColumn(name="id_agence")
	    private Agence  agence ;
}
