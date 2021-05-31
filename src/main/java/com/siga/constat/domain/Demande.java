package com.siga.constat.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings({ "serial", "unused" })
@Entity
@Table(name="Demande")

public class Demande implements Serializable {
	
 @OneToMany(mappedBy="demande")
	@JsonIgnore
	private Set<DemandeConducteur> demandeconducteurs;
 

 
 @OneToMany(mappedBy="demande")
	@JsonIgnore
	private Set<DemandeVoiture> demandevoitures;
 
 @OneToMany(mappedBy="demande")
	@JsonIgnore
	private Set<VictimeDemande> victimedemandes;
 /*
 @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
 @JoinTable(name = "demandes_assures",
         joinColumns = {
                 @JoinColumn(name = "demandes_id", referencedColumnName = "id",
                         nullable = false, updatable = false)},
         inverseJoinColumns = {
                 @JoinColumn(name = "assures_id", referencedColumnName = "id",
                         nullable = false, updatable = false)})
 private Set<Assure> assures = new HashSet<>();
 */
 @ManyToOne
 @JoinColumn(name="id_assure")
    private Assure assure ;
	
 
	 @ManyToOne
	 @JoinColumn(name="id_conducteur")
	    private Conducteur conducteur ;
	 @ManyToOne
	 @JoinColumn(name="expertsinistre")
	    private ExpertSinistre expertsinistre ;
	 
	 @ManyToOne
	    @JoinColumn(name="id_agence")
	    private Agence agence;
		
	 @ManyToOne
	    @JoinColumn(name="id_gestionnaire")
	    private  Gestionnaire gestionnaire;
	 @ManyToOne
	    @JoinColumn(name="id_historique")
	    private  Historique historique;
	
	 @ManyToOne
	    @JoinColumn(name="id_dossier")
	    private  Dossier  dossier;
	 
	 
		
	 
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  reference ;

	/*history */
	@Column(name="date_op")
	private LocalDateTime dateOp;
	
	@Column(name="op")
	private String op ;
	
	@Column(name="util")
	private String util;

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
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

	


	
}
