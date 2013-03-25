package com.home.main.db.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.home.main.fuzzyset.FuzzySet;
import com.home.main.fuzzyset.FuzzySetImpl;

@Entity
@Table(name = "fuzzyset")
public class FuzzySetDO implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private String name;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variable_id")
	private VariableDO variable;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "func_id")
	private FuncDO func;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "fuzzyset")
	private StatementDO statement;
	
	public FuzzySetDO(){
	}

	public FuzzySetDO(Integer id, String name, VariableDO variable, FuncDO func,
			StatementDO statement) {
		this.id = id;
		this.name = name;
		this.variable = variable;
		this.func = func;
		this.statement = statement;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public VariableDO getVariable() {
		return variable;
	}

	public FuncDO getFunc() {
		return func;
	}

	public StatementDO getStatement() {
		return statement;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVariable(VariableDO variable) {
		this.variable = variable;
	}

	public void setFunc(FuncDO func) {
		this.func = func;
	}

	public void setStatement(StatementDO statement) {
		this.statement = statement;
	}
	
	public FuzzySet getDTO(){
		return new FuzzySetImpl(id, name, func.getDTO());
	}

}
