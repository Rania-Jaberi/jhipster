package com.siga.constat.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Pm")
public class  Pmorale extends Assure {


@Column(name="raison_social")
private String raisonsocial;


@Column(name="matricule_fiscal")
private String matriculefiscal;

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


public String getRaisonsocial() {
return raisonsocial;
}


public void setRaisonsocial(String raisonsocial) {
this.raisonsocial = raisonsocial;
}


public String getMatriculefiscal() {
return matriculefiscal;
}


public void setMatriculefiscal(String matriculefiscal) {
this.matriculefiscal = matriculefiscal;
} }
