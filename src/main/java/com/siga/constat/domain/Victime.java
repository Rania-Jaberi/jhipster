package com.siga.constat.domain;

import java.io.Serializable;
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
@Table(name="victime")
public class Victime implements Serializable{

	
	
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


@OneToMany(mappedBy="victime")
@JsonIgnore
private Set<DossierVictime> dossiervictimes;

@OneToMany(mappedBy="victime")
@JsonIgnore
private Set<RapportVictime> rapportvictimes;

@OneToMany(mappedBy="victime")
@JsonIgnore
private Set<IndemnisationVictime> indemnisationvictimes;

@OneToMany(mappedBy="victime")
@JsonIgnore
private Set<VoitureVictime> voiturevictimes;

@OneToMany(mappedBy="victime")
@JsonIgnore
private Set<VictimeDemande> victimedemandes ;

@OneToMany(mappedBy="victime")
@JsonIgnore
private Set<AssureVictime> AssureVictimes ;


public Set<AssureVictime> getAssureVictimes() {
	return AssureVictimes;
}

public void setAssureVictimes(Set<AssureVictime> assureVictimes) {
	AssureVictimes = assureVictimes;
}


@ManyToOne
@JoinColumn(name="id_conducteur")
private Conducteur conducteur ;



@ManyToOne
@JoinColumn(name="id_gestionnaires")
private Gestionnaire gestionnaire;

 @ManyToOne
    @JoinColumn(name="id_historique")
    private  Historique historique;
 
 @ManyToOne
    @JoinColumn(name="id_demande")
 private Demande demande;
 
 
 
 
 

}