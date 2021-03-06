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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.home.main.variable.Statement;

@Entity
@Table(name = "statement")
@NamedQuery(name = "Statements", query = "select item from StatementDO as item")
public class StatementDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6626463182827277248L;

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variable_id")
	private VariableDO variable;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fuzzyset_id")
	private FuzzySetDO fuzzyset;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "modificator_id")
	private ModificatorDO modificator;

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "exprstate", joinColumns = @JoinColumn(name = "statement_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "expression_id", referencedColumnName = "ID"))
	private Set<ExpressionDO> expression;

	@Column(nullable = true)
	private Double weight;

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public StatementDO() {

	}

	public Integer getId() {
		return id;
	}

	public StatementDO(Integer id, VariableDO variable, FuzzySetDO fuzzyset,
			ModificatorDO modificator, Set<ExpressionDO> expression,
			Double weight) {
		this.id = id;
		this.variable = variable;
		this.fuzzyset = fuzzyset;
		this.modificator = modificator;
		this.expression = expression;
		this.weight = weight;
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

	public void setId(Integer id) {
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

	public Set<ExpressionDO> getExpression() {
		return expression;
	}

	public void setExpression(Set<ExpressionDO> expression) {
		this.expression = expression;
		for(ExpressionDO e : this.expression){
			e.addStatement(this);
		}
	}

	public Statement getDTO() {
		return new Statement(id, fuzzyset.getDTO(), variable.getDTO(),
				(modificator != null) ? modificator.getDTO() : null);
	}
	
	public void addExpression(ExpressionDO e){
		if (expression == null){
			expression = new HashSet<ExpressionDO>();
		}
		expression.add(e);
	}

}
