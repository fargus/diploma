package com.home.main.db.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rule")
public class RuleDO {
	
	@Id
	@GeneratedValue
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cond_id", referencedColumnName = "id")
	private ExpressionDO cond;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conc_id", referencedColumnName = "id")
	private ExpressionDO conc;
	
	public RuleDO() {
	}

	public RuleDO(int id, ExpressionDO cond, ExpressionDO conc) {
		this.id = id;
		this.cond = cond;
		this.conc = conc;
	}

	public int getId() {
		return id;
	}

	public ExpressionDO getCond() {
		return cond;
	}

	public ExpressionDO getConc() {
		return conc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCond(ExpressionDO cond) {
		this.cond = cond;
	}

	public void setConc(ExpressionDO conc) {
		this.conc = conc;
	}
	
}
