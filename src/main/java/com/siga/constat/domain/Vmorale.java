package com.siga.constat.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Vm")
public class Vmorale extends Victime{

@Column(name="Imatriculation")
private String  mastriculation;


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

@Column(name="type")
private String  type;

public String getMastriculation() {
	return mastriculation;
}

public void setMastriculation(String mastriculation) {
	this.mastriculation = mastriculation;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}





}
