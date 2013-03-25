package com.home.main.db.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.home.main.variable.Modificator;

@Entity
@Table(name = "modificator")
public class ModificatorDO {

	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name = "variable_id")
	private VariableDO variable;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "modificator")
	private StatementDO statement;
	
	public ModificatorDO(){
	}

	public ModificatorDO(int id, String name, VariableDO variable,
			StatementDO statement) {
		this.id = id;
		this.name = name;
		this.variable = variable;
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

	public void setStatement(StatementDO statement) {
		this.statement = statement;
	}
	
	public Modificator getDTO(){
		return Modificator.getModById(id);
	}
}
