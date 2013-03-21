package com.home.main.db.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "variable")
public class VariableDO {
	
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private double min;
	@Column(nullable = false)
	private double max;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "variable")
	private Set<FuzzySetDO> terms;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "variable")
	private StatementDO statement;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "variable")
	private Set<ModificatorDO> mod;
	
	public VariableDO(){
	}

	public VariableDO(int id, String name, double min, double max,
			Set<FuzzySetDO> terms, StatementDO statement, Set<ModificatorDO> mod) {
		this.id = id;
		this.name = name;
		this.min = min;
		this.max = max;
		this.terms = terms;
		this.statement = statement;
		this.mod = mod;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public Set<FuzzySetDO> getTerms() {
		return terms;
	}

	public StatementDO getStatement() {
		return statement;
	}

	public Set<ModificatorDO> getMod() {
		return mod;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public void setTerms(Set<FuzzySetDO> terms) {
		this.terms = terms;
	}

	public void setStatement(StatementDO statement) {
		this.statement = statement;
	}

	public void setMod(Set<ModificatorDO> mod) {
		this.mod = mod;
	}

}
