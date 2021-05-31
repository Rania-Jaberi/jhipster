package com.siga.constat.domain;

import java.io.Serializable;
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

@SuppressWarnings({ "serial" })
@Entity
@Table(name="voiture")
public class Voiture implements Serializable{
	
	@Id
	@Column(name="N_immatriculation")
	private Long N_immatriculation;
	
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



	@Column(name="pay_immatriculation")
	private String pay_immatriculation ;
	
	
	@Column(name="type")
	private String type;
	
	
	public Long getN_immatriculation() {
		return N_immatriculation;
	}


	public void setN_immatriculation(Long n_immatriculation) {
		n_immatriculation = N_immatriculation;
	}


	public String getPay_immatriculation() {
		return pay_immatriculation;
	}


	public void setPay_immatriculation(String pay_immatriculation) {
		this.pay_immatriculation = pay_immatriculation;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
    
	

/*	@OneToMany(mappedBy="Voiture")
	@JsonIgnore
	private Set<VoitureVictime> voiturevictimes;  */
 
    @OneToMany(mappedBy="voiture")
	@JsonIgnore
	private Set<ConducteurVoiture> conducteurvoitures;
 
    @OneToMany(mappedBy="voiture")
	@JsonIgnore
	private Set<DemandeVoiture> demandevoitures;
 
 
 
   	@ManyToOne
    @JoinColumn(name="id_assuree")
    private Assure assure;	
	 
	@ManyToOne
    @JoinColumn(name="id_gestionnaires")
    private Gestionnaire gestionnaire;
	
	 @ManyToOne
	 @JoinColumn(name="id_historique")
     private  Historique historique;
	 
	  @ManyToOne
      @JoinColumn(name="id_rapport")
      private   Rapport rapport;
	 
	 @ManyToOne
     @JoinColumn(name="id_dossier")
     private  Dossier  dossier;
	

	
	
	
	}
	