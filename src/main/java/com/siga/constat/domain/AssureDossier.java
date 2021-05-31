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
@Table(name="assureur_dossier")
public class AssureDossier implements Serializable {

	
	
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
public Assure getAssure() {
	return assure;
}
public void setAssure(Assure assure) {
	this.assure = assure;
}
public Dossier getDossier() {
	return dossier;
}
public void setDossier(Dossier dossier) {
	this.dossier = dossier;
}
/*
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
*/
@ManyToOne
@JoinColumn(name="id_assure")
private Assure assure;
@ManyToOne
@JoinColumn(name="id_dossier")
private  Dossier  dossier;
}
