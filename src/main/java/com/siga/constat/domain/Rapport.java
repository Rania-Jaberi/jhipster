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
@Table(name="Rapport")
public class Rapport implements Serializable{
	
	
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


@Column(name="Date_accident")
private LocalDate Date_accident;
public Long getNum() {
return Num;
}

public void setNum(Long num) {
Num = num;
}


public LocalDate getDate_accident() {
return Date_accident;
}

public void setDate_accident(LocalDate date_accident) {
Date_accident = date_accident;
}


@OneToMany(mappedBy="rapport")
@JsonIgnore
private Set<ConducteurRapport> conducteurrapports;

@OneToMany(mappedBy="rapport")
@JsonIgnore
private Set<RapportVictime> rapportvictimes;

@OneToMany(mappedBy="rapport")
@JsonIgnore
private Set<Voiture> voitures;

@OneToMany(mappedBy="rapport")
@JsonIgnore
private Set<AssureRapport> assurerapports;



 @ManyToOne
 @JoinColumn(name="comptable")
   private Comptable comptable ;
 @ManyToOne
 @JoinColumn(name="expertsinistre")
   private ExpertSinistre expertsinistre ;


@ManyToOne
@JoinColumn(name="id_historique")
private Historique  historique;
@ManyToOne
@JoinColumn(name="id_responsableSiege")
private ResponsableSiege  responsableSiege;





}

