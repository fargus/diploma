package com.home.main.db.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.rule.Rule;
import com.home.main.variable.Operator;

@Entity
@Table(name = "rule")
@NamedQuery(name="Rules", query="select item from RuleDO as item left join item.cond as cond left join item.conc as conc")
public class RuleDO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9161827429181628064L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cond_id", referencedColumnName = "id")
	private ExpressionDO cond;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conc_id", referencedColumnName = "id")
	private ExpressionDO conc;
	
	public RuleDO() {
	}

	public RuleDO(ExpressionDO cond, ExpressionDO conc) {
		this.cond = cond;
		this.conc = conc;
	}

	public Integer getId() {
		return id;
	}

	public ExpressionDO getCond() {
		return cond;
	}

	public ExpressionDO getConc() {
		return conc;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCond(ExpressionDO cond) {
		this.cond = cond;
	}

	public void setConc(ExpressionDO conc) {
		this.conc = conc;
	}
	
	public Rule getDTO(){
		List<Condition> conds = new ArrayList<Condition>();
		List<Conclusion> concs = new ArrayList<Conclusion>();
		for (StatementDO s : cond.getStatement()){
			Condition c = new Condition(s.getDTO());
			//c.setOperator(Operator.getOp(cond.getOp()));
			conds.add(c);
			
		}
		
		for (StatementDO s : conc.getStatement()){
			Conclusion c = new Conclusion(s.getDTO());
			//c.setOperator(Operator.getOp(cond.getOp()));
			c.setWeight(s.getWeight());
			concs.add(c);
			
		}
		Rule r = new Rule(id, conds, concs);
		r.setCondO(Operator.getOp(cond.getOp()));
		r.setConcO(Operator.getOp(cond.getOp()));
		return new Rule(id, conds, concs);
	}
	
}
