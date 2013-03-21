package com.home.main.db.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "expression")
public class ExpressionDO {

	@Id
	@GeneratedValue
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	private int op;
	
	public int getOp() {
		return op;
	}

	public void setOp(int op) {
		this.op = op;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "expression")
	private Set<ExprStateDO> exprstate;

	public Set<ExprStateDO> getExprstate() {
		return exprstate;
	}

	public void setExprstate(Set<ExprStateDO> exprstate) {
		this.exprstate = exprstate;
	}

	public ExpressionDO(){
		
	}
}
