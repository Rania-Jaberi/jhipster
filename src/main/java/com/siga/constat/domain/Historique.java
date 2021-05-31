package com.siga.constat.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name="historique")

public class Historique implements Serializable {
	
	@Id
	@Column(name="ID")
	private Long  id ;
	
	@Column(name="Dates")
	private LocalDate dates ;
	
	@Column(name="Ancien_demandes")
	private String ancien_demandes ;
	
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
	public LocalDate getDates() {
		return dates;
	}
	public void setDates(LocalDate dates) {
		this.dates = dates;
	}
	public String getAncien_demandes() {
		return ancien_demandes;
	}
	public void setAncien_demandes(String ancien_demandes) {
		this.ancien_demandes = ancien_demandes;
	}
	@OneToMany(mappedBy="historique")
	@JsonIgnore
	private Set<Demande>demandes;
	
	@OneToMany(mappedBy="historique")
	@JsonIgnore
	private Set<Victime> victimes;
	
	@OneToMany(mappedBy="historique")
	@JsonIgnore
	private Set<Rapport> rapports;
	
	@OneToMany(mappedBy="historique")
	@JsonIgnore
	private Set<Dossier> dossiers;
	
	@OneToMany(mappedBy="historique")
	@JsonIgnore
	private Set<Assure>assure;
	
	@OneToMany(mappedBy="historique")
	@JsonIgnore
	private Set<Conducteur>conducteurs ;
	
	@OneToMany(mappedBy="historique")
	@JsonIgnore
	private Set<Voiture> voitures ;
	
	 @ManyToOne
	    @JoinColumn(name="id_assurance")
	    private Assurance  assurance ;
	 @ManyToOne
	    @JoinColumn(name="id_agence")
	    private Agence agence;
	 
}
