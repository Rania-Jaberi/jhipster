package com.siga.constat.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Indemnisation")
public class Indemnisation implements Serializable{

	 
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long reference;

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




@Column(name="Date")
private LocalDate Date;

@Column(name="montant_argent")
private Long montant_argent;

/*

@Column(name="Assuree")
private Assure assuree;

@Column(name="Voiture")
private Voiture voiture;

*/
public Long getReference() {
	return reference;
}

public void setReference(Long reference) {
	this.reference = reference;
}

public LocalDate getDate() {
	return Date;
}

public void setDate(LocalDate date) {
	Date = date;
}

public Long getMontant_argent() {
	return montant_argent;
}

public void setMontant_argent(Long montant_argent) {
	this.montant_argent = montant_argent;
}




@OneToMany(mappedBy="indemnisation")
@JsonIgnore
private Set<IndemnisationVictime> indemnisationvictimes;

@OneToMany(mappedBy="indemnisation")
@JsonIgnore
private Set<AssureIndemnisation> assureindemnisations;

 @ManyToOne
    @JoinColumn(name="id_chef_agence")
    private ChefAgence chefAgence ;

}




