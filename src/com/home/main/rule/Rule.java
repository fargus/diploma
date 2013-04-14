package com.home.main.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.home.main.db.entities.ExpressionDO;
import com.home.main.db.entities.RuleDO;
import com.home.main.db.entities.StatementDO;
import com.home.main.variable.Operator;

public class Rule{
	
	private Integer id;
	
	private List<Condition> conditions;
	private List<Conclusion> conclusions;
	private Operator condO = Operator.AND;
	private Operator concO = Operator.AND;
	
	public Rule(Integer id, Condition condition, Conclusion conclusion) {
		this.id = id;
		this.conclusions = new ArrayList<Conclusion>();
		this.conditions = new ArrayList<Condition>();
		
		if (conclusion == null){
			throw new RuntimeException("Supplied conclusion is empty");
		}
		if (condition == null){
			throw new RuntimeException("Supplied condition is empty");
		}
		conclusions.add(conclusion);
		conditions.add(condition);
	}
	
	public Rule(Condition condition, Conclusion conclusion) {
		this(null, condition, conclusion);
	}
	
	public Rule(Integer id, List<Condition> conditions, List<Conclusion> conclusions) {
		this.id = id;
		this.conclusions = conclusions;
		this.conditions = conditions;
	}
	
	public Rule(List<Condition> conditions, List<Conclusion> conclusions) {
		this.conclusions = conclusions;
		this.conditions = conditions;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public List<Conclusion> getConclusions() {
		return conclusions;
	}

	public void setConclusions(List<Conclusion> conclusions) {
		this.conclusions = conclusions;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public void addConclusion(Conclusion conclusion){
		conclusions.add(conclusion);
	}
	
	public void addCondition(Condition condition){
		conditions.add(condition);
	}

	public void removeConclusion(Conclusion conclusion){
		conclusions.remove(conclusion);
	}
	
	public void removeCondition(Condition condition){
		conditions.remove(condition);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("IF ");
		Iterator<Condition> itr = conditions.iterator();
		if(itr.hasNext()){
			sb.append(itr.next().getName());
		}
		while(itr.hasNext()){
			Condition next = itr.next();
			sb.append(" "+condO+" ");
			sb.append(next.getName());
		}
		sb.append(" THEN ");
		Iterator<Conclusion> itr2 = conclusions.iterator();
		if(itr2.hasNext()){
			sb.append(itr2.next().getName());
		}
		while(itr2.hasNext()){
			Conclusion next = itr2.next();
			sb.append(" "+concO+" ");
			sb.append(next.getName());
		}
		return "RULE:"+((id != null)?id:"#")+" ["+sb.toString()+"]";
	}
	
	public RuleDO getDO(){
		ExpressionDO cond = new ExpressionDO();
		ExpressionDO conc = new ExpressionDO();
		cond.setOp(this.getCondO().getCode());
		conc.setOp(this.getConcO().getCode());
		
		Set<StatementDO> conds = new HashSet<StatementDO>();
		Set<StatementDO> concs = new HashSet<StatementDO>();
		for (Condition c : getConditions()){
			StatementDO s = c.getSDO();
			s.addExpression(cond);
			conds.add(s);
		}
		for (Conclusion c : getConclusions()){
			StatementDO s = c.getSDO();
			s.addExpression(conc);
			concs.add(c.getSDO());
		}
		
		cond.setStatement(conds);
		conc.setStatement(concs);
		return new RuleDO(cond, conc);
	}

	public Operator getCondO() {
		return condO;
	}

	public void setCondO(Operator condO) {
		this.condO = condO;
	}

	public Operator getConcO() {
		return concO;
	}

	public void setConcO(Operator concO) {
		this.concO = concO;
	}
	
}
