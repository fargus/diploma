package com.home.main.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Rule {
	
	private int id;
	
	private List<Condition> conditions;
	private List<Conclusion> conclusions;
	
	public Rule(int id) {
		this.id = id;
		this.conclusions = new ArrayList<Conclusion>();
		this.conditions = new ArrayList<Condition>();
	}
	
	public Rule(int id, Condition condition, Conclusion conclusion) {
		this(id);
		
		if (conclusion == null){
			throw new RuntimeException("Supplied conclusion is empty");
		}
		if (condition == null){
			throw new RuntimeException("Supplied condition is empty");
		}
		conclusions.add(conclusion);
		conditions.add(condition);
	}
	
	public Rule(int id, List<Condition> conditions, List<Conclusion> conclusions) {
		this.id = id;
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
	
	public int getId(){
		return id;
	}
	
	public void addConclusion(Conclusion conclusion){
		conclusions.add(conclusion);
	}
	
	public void addCondition(Condition condition){
		conditions.add(condition);
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
			sb.append(" "+next.getOperator()+" ");
			sb.append(next.getName());
		}
		sb.append(" THEN ");
		Iterator<Conclusion> itr2 = conclusions.iterator();
		if(itr2.hasNext()){
			sb.append(itr2.next().getName());
		}
		while(itr2.hasNext()){
			Conclusion next = itr2.next();
			sb.append(" "+next.getOperator()+" ");
			sb.append(next.getName());
		}
		return "RULE:"+id+" ["+sb.toString()+"]";
	}
	
}