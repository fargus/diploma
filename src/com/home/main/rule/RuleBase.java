package com.home.main.rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.home.main.func.FuncType;
import com.home.main.func.FuncUtil;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.fuzzyset.FuzzySetImpl;
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
	
	public static RuleBase getTestBase(){

		Variable v1 = new Variable(1, "water temp", 0, 100);
		
		FuzzySet fs1 = new FuzzySetImpl(1, "cold", FuncUtil.createFunc(10, 30, FuncType.Z));
		FuzzySet fs2 = new FuzzySetImpl(2, "cool", FuncUtil.createFunc(20, 35, 50, FuncType.TRIANGLE));
		FuzzySet fs3 = new FuzzySetImpl(3, "warm", FuncUtil.createFunc(40, 50, 60, FuncType.TRIANGLE));
		FuzzySet fs4 = new FuzzySetImpl(4, "not hot", FuncUtil.createFunc(50, 60, 70, FuncType.TRIANGLE));
		FuzzySet fs5 = new FuzzySetImpl(5, "hot", FuncUtil.createFunc(60, 70, FuncType.S));
		
		v1.addFuzzySet(fs1);
		v1.addFuzzySet(fs2);
		v1.addFuzzySet(fs3);
		v1.addFuzzySet(fs4);
		v1.addFuzzySet(fs5);
		
		Variable v2 = new Variable(2, "angle", -90, 90);
		
		FuzzySet fs6 = new FuzzySetImpl(6, "full left", FuncUtil.createFunc(-72, -36, FuncType.Z));
		FuzzySet fs7 = new FuzzySetImpl(7, "left", FuncUtil.createFunc(-54, -27, 0, FuncType.TRIANGLE));
		FuzzySet fs8 = new FuzzySetImpl(8, "center", FuncUtil.createFunc(-18, 0, 18, FuncType.TRIANGLE));
		FuzzySet fs9 = new FuzzySetImpl(9, "right", FuncUtil.createFunc(0, 27, 54, FuncType.TRIANGLE));
		FuzzySet fs10 = new FuzzySetImpl(10, "full right", FuncUtil.createFunc(36, 72, FuncType.S));
		
		v2.addFuzzySet(fs6);
		v2.addFuzzySet(fs7);
		v2.addFuzzySet(fs8);
		v2.addFuzzySet(fs9);
		v2.addFuzzySet(fs10);
		
		Rule r1 = new Rule(1, new Condition(1,fs5, v1), new Conclusion(6, fs10, v2));
		Rule r2 = new Rule(2, new Condition(2,fs4, v1), new Conclusion(7, fs9, v2));
		Rule r3 = new Rule(3, new Condition(3,fs3, v1), new Conclusion(8, fs8, v2));
		Rule r4 = new Rule(4, new Condition(4,fs2, v1), new Conclusion(9, fs7, v2));
		Rule r5 = new Rule(5, new Condition(5,fs1, v1), new Conclusion(10, fs6, v2));
		
		return new RuleBase().addRule(r1).addRule(r2).addRule(r3).addRule(r4).addRule(r5);
	}

}
