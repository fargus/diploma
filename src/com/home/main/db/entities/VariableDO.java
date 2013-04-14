package com.home.main.db.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.home.main.fuzzyset.FuzzySet;
import com.home.main.variable.Modificator;
import com.home.main.variable.Variable;

@Entity
@Table(name = "variable")
public class VariableDO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -810685299280094901L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private double min;
	@Column(nullable = false)
	private double max;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "variable")
	private Set<FuzzySetDO> terms;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "variable")
	private Set<StatementDO> statement;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "variable")
	private Set<ModificatorDO> mod;
	
	public VariableDO(){
	}

	public VariableDO(Integer id, String name, double min, double max,
			Set<FuzzySetDO> terms, Set<StatementDO> statement, Set<ModificatorDO> mod) {
		this.id = id;
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

	public Set<StatementDO> getStatement() {
		return statement;
	}

	public Set<ModificatorDO> getMod() {
		return mod;
	}

	public void setId(Integer id) {
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
			fs.addVariable(this);
		}
	}

	public void setStatement(Set<StatementDO> statement) {
		this.statement = statement;
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
		return new Variable(id, name, terms, min, max, (mod.isEmpty())?null:mod);
	}
}
