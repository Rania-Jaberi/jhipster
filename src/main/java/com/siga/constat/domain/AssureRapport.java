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

@Entity
@Table(name="AssureRapport")
public class AssureRapport implements Serializable{

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

/*
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
*/
public Long getNum() {
	return Num;
}

public void setNum(Long num) {
	Num = num;
}



@ManyToOne
@JoinColumn(name="id_assure")
private Assure assure;

@ManyToOne
@JoinColumn(name="id_rapport")
private Rapport rapport;
}
