package com.siga.constat.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
@DiscriminatorValue("Ca")
public class OrdreDeMission {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  num ;
	
	@Column(name="Nature_de_garantie")
	private String Nature_de_garantie ;
	
	
	@Column(name="Lieu_repartition")
	private String Lieu_repartition ;

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


	public Long getNum() {
		return num;
	}


	public void setNum(Long num) {
		this.num = num;
	}


	public String getNature_de_garantie() {
		return Nature_de_garantie;
	}


	public void setNature_de_garantie(String nature_de_garantie) {
		Nature_de_garantie = nature_de_garantie;
	}


	public String getLieu_repartition() {
		return Lieu_repartition;
	}


	public void setLieu_repartition(String lieu_repartition) {
		Lieu_repartition = lieu_repartition;
	}
	
	

}
