package com.home.main.db.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.home.main.fuzzyset.FuzzySet;
import com.home.main.fuzzyset.FuzzySetImpl;

@Entity
@Table(name = "fuzzyset")
public class FuzzySetDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8400420080083929343L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private String name;
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "termvar", joinColumns = @JoinColumn(name = "fuzzyset_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "variable_id", referencedColumnName = "ID"))
	private Set<VariableDO> variable;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "func_id")
	private FuncDO func;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fuzzyset", fetch=FetchType.LAZY)
	private Set<StatementDO> statement;
	
	public FuzzySetDO(){
	}

	public FuzzySetDO(Integer id, String name, Set<VariableDO> variable, FuncDO func,
			Set<StatementDO> statement) {
		this.id = id;
		this.name = name;
		this.variable = variable;
		this.func = func;
		this.statement = statement;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<VariableDO> getVariable() {
		return variable;
	}

	public FuncDO getFunc() {
		return func;
	}

	public Set<StatementDO> getStatement() {
		return statement;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVariable(Set<VariableDO> variable) {
		this.variable = variable;
	}

	public void setFunc(FuncDO func) {
		this.func = func;
	}

	public void setStatement(Set<StatementDO> statement) {
		this.statement = statement;
	}
	
	public void addVariable(VariableDO v){
		variable.add(v);
	}
	
	public FuzzySet getDTO(){
		return new FuzzySetImpl(id, name, func.getDTO());
	}

}
