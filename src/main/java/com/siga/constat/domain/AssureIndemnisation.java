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
@Table(name="AssureIndemnisation")
public class AssureIndemnisation implements Serializable{
	
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
	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
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

	public Indemnisation getIndemnisation() {
		return indemnisation;
	}

	public void setIndemnisation(Indemnisation indemnisation) {
		this.indemnisation = indemnisation;
	}

	public Assure getAssure() {
		return assure;
	}

	public void setAssure(Assure assure) {
		this.assure = assure;
	}

	@ManyToOne
	  @JoinColumn(name="id_indemnisation")
	 private  Indemnisation indemnisation;
	
	@ManyToOne
    @JoinColumn(name="id_assure")
    private Assure assure;

}
