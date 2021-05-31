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

import antlr.collections.List;

	
	@Entity
	@Table(name="assurance")
	public class Assurance implements Serializable{
		
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long  id ;
		
		@Column(name="Num_contrat")
		private String num_contrat;
		
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

		public Set<ChefAgence> getChef_agences() {
			return chef_agences;
		}

		public void setChef_agences(Set<ChefAgence> chef_agences) {
			this.chef_agences = chef_agences;
		}

		public Set<Gestionnaire> getGestionnaires() {
			return gestionnaires;
		}

		public void setGestionnaires(Set<Gestionnaire> gestionnaires) {
			this.gestionnaires = gestionnaires;
		}

		public Set<ExpertSinistre> getExpert_sinistres() {
			return expert_sinistres;
		}

		public void setExpert_sinistres(Set<ExpertSinistre> expert_sinistres) {
			this.expert_sinistres = expert_sinistres;
		}

		public Set<Dossier> getDossiers() {
			return dossiers;
		}

		public void setDossiers(Set<Dossier> dossiers) {
			this.dossiers = dossiers;
		}

		public Set<Historique> getHistoriques() {
			return historiques;
		}

		public void setHistoriques(Set<Historique> historiques) {
			this.historiques = historiques;
		}

		public Set<Agence> getAgences() {
			return agences;
		}

		public void setAgences(Set<Agence> agences) {
			this.agences = agences;
		}

		public Rapport getRapport() {
			return rapport;
		}

		public void setRapport(Rapport rapport) {
			this.rapport = rapport;
		}

		@Column(name="adresse")
		private String adresse ;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNum_contrat() {
			return num_contrat;
		}

		public void setNum_contrat(String num_contrat) {
			this.num_contrat = num_contrat;
		}

		

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}
		
		@OneToMany(mappedBy="assurance")
		@JsonIgnore
		private Set<ChefAgence> chef_agences;
		
		@OneToMany(mappedBy="assurance")
		@JsonIgnore
		private Set<Gestionnaire> gestionnaires;
		
		@OneToMany(mappedBy="assurance")
		@JsonIgnore
		private Set<ExpertSinistre> expert_sinistres;
		@OneToMany(mappedBy="assurance")
		@JsonIgnore
		private Set<Dossier> dossiers;
		@OneToMany(mappedBy="assurance")
		@JsonIgnore
		private Set<Historique> historiques;
		
		@OneToMany(mappedBy="assurance")
		@JsonIgnore
		private Set<Agence> agences;
		 @ManyToOne
		    @JoinColumn(name="id_rapport")
		    private  Rapport rapport;
		
   	
	
	}


 
