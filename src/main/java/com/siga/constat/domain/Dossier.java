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
@Table(name="Dossier")
public class Dossier implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Num;
	
	/*history */
	@Column(name="date_op")
	private LocalDateTime dateOp;
	
	@Column(name="op")
	private String op ;
	
	@Column(name="util")
	private String util;
	
public Long getNum() {
		return Num;
	}
	public void setNum(Long num) {
		Num = num;
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
@OneToMany(mappedBy="dossier")
@JsonIgnore
private Set<AssureDossier> assuresdossiers;

@OneToMany(mappedBy="dossier")
@JsonIgnore
private Set<DossierConducteur> dossierconducteurs;

@OneToMany(mappedBy="dossier")
@JsonIgnore
private Set<Demande> demandes;

@OneToMany(mappedBy="dossier")
@JsonIgnore
private Set<Voiture> voitures;

@OneToMany(mappedBy="dossier")
@JsonIgnore
private Set<DossierVictime> dossiervictimes;


@ManyToOne
@JoinColumn(name="id_assurance")
private Assurance  assurance;
@ManyToOne
@JoinColumn(name="id_agence")
private Agence  agence;

@ManyToOne
@JoinColumn(name="id_historique")
private Historique  historique;

@ManyToOne
@JoinColumn(name="id_expertsinistre")
private ExpertSinistre expertsinistre;
@ManyToOne
 @JoinColumn(name="comptable")
   private Comptable comptable ;
@ManyToOne
@JoinColumn(name="id_responsableSiege")
private ResponsableSiege  responsableSiege;




}

