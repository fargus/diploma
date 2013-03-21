package com.home.main.rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.home.main.variable.Variable;

public class RuleBase {
	
	private static final Logger log = Logger.getLogger(RuleBase.class);
	
	private Set<Variable> inputVars;
	private Set<Variable> outputVars;
	
	private Map<Integer, Rule> rules;
	
	public RuleBase(){
		rules = new HashMap<Integer, Rule>();
		inputVars = new HashSet<Variable>();
		outputVars = new HashSet<Variable>();
	}
	
	public RuleBase addRule(Rule rule){
		this.rules.put(rule.getId(), rule);
		for(Condition c : rule.getConditions()){
			inputVars.add(c.getVar());
		}
		for(Conclusion c : rule.getConclusions()){
			outputVars.add(c.getVar());
		}
		
		log.trace(rule.toString());
		
		return this;
	}
	
	public Collection<Rule> getRules(){
		return rules.values();
	}
	
	public Rule getRule(int id){
		return rules.get(id);
	}
	
	public int getNumRules(){
		return rules.size();
	}
	
	public Set<Variable> getInputVars() {
		return inputVars;
	}

	public Set<Variable> getOutputVars() {
		return outputVars;
	}
	
	public Set<Condition> getConditions(){
		Set<Condition> result = new HashSet<Condition>();
		for(Rule r : rules.values()){
			result.addAll(r.getConditions());
		}
		return result;
	}
	
	public Set<Conclusion> getConclusions(){
		Set<Conclusion> result = new HashSet<Conclusion>();
		for(Rule r : rules.values()){
			result.addAll(r.getConclusions());
		}
		return result;
	}

}
