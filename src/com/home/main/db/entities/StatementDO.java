package com.home.main.db.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="statement")
public class StatementDO {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variable_id")
	private VariableDO variable;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fuzzyset_id")
	private FuzzySetDO fuzzyset;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "modificator_id")
	private ModificatorDO modificator;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "statement")
	private Set<ExprStateDO> exprstate;

	public StatementDO(){
		
	}

	public int getId() {
		return id;
	}

	public VariableDO getVariable() {
		return variable;
	}

	public FuzzySetDO getFuzzyset() {
		return fuzzyset;
	}

	public ModificatorDO getModificator() {
		return modificator;
	}

	public Set<ExprStateDO> getExprstate() {
		return exprstate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVariable(VariableDO variable) {
		this.variable = variable;
	}

	public void setFuzzyset(FuzzySetDO fuzzyset) {
		this.fuzzyset = fuzzyset;
	}

	public void setModificator(ModificatorDO modificator) {
		this.modificator = modificator;
	}

	public void setExprstate(Set<ExprStateDO> exprstate) {
		this.exprstate = exprstate;
	}
	
	

}
