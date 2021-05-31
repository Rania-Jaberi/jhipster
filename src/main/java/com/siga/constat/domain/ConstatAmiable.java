package com.siga.constat.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings({ "serial", "unused" })
@Entity
@DiscriminatorValue("Ca")
public class ConstatAmiable implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  num ;
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

	@Column(name="circonstance")
	private String circonstance;
	
	
	@Column(name="Lieux_accident")
	private String Lieux_accident;
	
	@Column(name="Date_accident")
	private String Date_accident;

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getCirconstance() {
		return circonstance;
	}

	public void setCirconstance(String circonstance) {
		this.circonstance = circonstance;
	}


	public String getLieux_accident() {
		return Lieux_accident;
	}

	public void setLieux_accident(String lieux_accident) {
		Lieux_accident = lieux_accident;
	}

	public String getDate_accident() {
		return Date_accident;
	}

	public void setDate_accident(String date_accident) {
		Date_accident = date_accident;
	}
}
