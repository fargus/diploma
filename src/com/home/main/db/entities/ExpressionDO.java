package com.home.main.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "expression")
public class ExpressionDO {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private int op;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "expression")
	private Set<StatementDO> statement;
	
	public Set<StatementDO> getStatement() {
		return statement;
	}

	public void setStatement(Set<StatementDO> statement) {
		this.statement = statement;
	    for(StatementDO s : this.statement){
	    	s.addExpression(this);
	    }
	}
	
	public int getOp() {
		return op;
	}

	public void setOp(int op) {
		this.op = op;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ExpressionDO(){
		
	}
	
	public void addStatement(StatementDO s){
		if (statement == null){
			statement = new HashSet<StatementDO>();
		}
		statement.add(s);
	}
}
