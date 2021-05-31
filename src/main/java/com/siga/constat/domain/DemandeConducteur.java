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
@Table(name="dmd_conducteur")
public class DemandeConducteur  implements Serializable{


@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

/*history */
@Column(name="date_op")
private LocalDateTime dateOp;

@Column(name="op")
private String op ;

@Column(name="util")
private String util;
/*
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long  reference ;
*/



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

@ManyToOne
@JoinColumn(name="id_conducteur")
private Conducteur conducteur;

@ManyToOne
@JoinColumn(name="id_demande")
private Demande demande;

}
