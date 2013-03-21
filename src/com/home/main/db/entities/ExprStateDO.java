package com.home.main.db.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "exprstatement")
@IdClass(ExprStatePK.class)
public class ExprStateDO implements Serializable {
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="statement_id", referencedColumnName="id")
	private StatementDO statement;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="expression_id", referencedColumnName="id")
	private ExpressionDO expression;
	
	@Column
	private Double weight;

	public StatementDO getStatement() {
		return statement;
	}

	public ExpressionDO getExpression() {
		return expression;
	}

	public Double getWeight() {
		return weight;
	}

	public void setStatement(StatementDO statement) {
		this.statement = statement;
	}

	public void setExpression(ExpressionDO expression) {
		this.expression = expression;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
}
