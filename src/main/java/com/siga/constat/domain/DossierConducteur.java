package com.siga.constat.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="DossierConducteur")
public class DossierConducteur implements Serializable {

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
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;*/
	
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

	public Long getNum() {
		return Num;
	}

	public void setNum(Long num) {
		Num = num;
	}

	@ManyToOne
    @JoinColumn(name="id_conducteur")
    private  Conducteur conducteur;
	
	@ManyToOne
    @JoinColumn(name="id_dossier")
    private  Dossier dossier;
}
