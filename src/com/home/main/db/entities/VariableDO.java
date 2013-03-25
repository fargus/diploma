package com.home.main.db.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.home.main.fuzzyset.FuzzySet;
import com.home.main.variable.Modificator;
import com.home.main.variable.Variable;

@Entity
@Table(name = "variable")
public class VariableDO implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
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

	public VariableDO(String name, double min, double max,
			Set<FuzzySetDO> terms, StatementDO statement, Set<ModificatorDO> mod) {
		this.name = name;
		this.min = min;
		this.max = max;
		setTerms(terms);
		setStatement(statement);
		setMod(mod);
	}

	public Integer getId() {
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
		for (FuzzySetDO fs : this.terms){
			fs.setVariable(this);
		}
	}

	public void setStatement(StatementDO statement) {
		if (statement != null){
			this.statement = statement;
			this.statement.setVariable(this);
		}
	}

	public void setMod(Set<ModificatorDO> mod) {
		this.mod = mod;
		for(ModificatorDO m : this.mod){
			m.setVariable(this);
		}
	}

	public Variable getDTO(){
		Set<FuzzySet> terms = new HashSet<FuzzySet>();
		Set<Modificator> mod = new HashSet<Modificator>();
		for (FuzzySetDO fs : this.terms){
			terms.add(fs.getDTO());
		}
		for (ModificatorDO m : this.mod){
			mod.add(m.getDTO());
		}
		return new Variable(id, name, terms, min, max, mod);
	}
}
