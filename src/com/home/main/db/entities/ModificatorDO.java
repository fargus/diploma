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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.home.main.variable.Modificator;

@Entity
@Table(name = "modificator")
public class ModificatorDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5294998862320972619L;
	
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false)
	private String name;
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "variable_id")
	private VariableDO variable;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "modificator")
	private Set<StatementDO> statement;
	
	public ModificatorDO(){
	}

	public ModificatorDO(Integer id, String name, VariableDO variable,
			Set<StatementDO> statement) {
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

	public Set<StatementDO> getStatement() {
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

	public void setStatement(Set<StatementDO> statement) {
		this.statement = statement;
	}
	
	public Modificator getDTO(){
		return Modificator.getModById(id);
	}
}
