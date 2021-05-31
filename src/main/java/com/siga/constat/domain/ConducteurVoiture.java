package com.siga.constat.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="ConducteurVoiture")
public class ConducteurVoiture implements Serializable{
	
	@Id
	@Column(name="N_immatriculation")
	private String N_immatriculation;
/*	
 * 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	*/
	
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

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Conducteur getConducteur() {
		return conducteur;
	}

	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}

	public String getN_immatriculation() {
		return N_immatriculation;
	}

	public void setN_immatriculation(String n_immatriculation) {
		N_immatriculation = n_immatriculation;
	}

	@ManyToOne
    @JoinColumn(name="id_voiture")
    private Voiture voiture;
	
	@ManyToOne
	 @JoinColumn(name="id_conducteur")
	    private Conducteur conducteur ;

}
