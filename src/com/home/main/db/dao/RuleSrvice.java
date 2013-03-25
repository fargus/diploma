package com.home.main.db.dao;

import com.home.main.func.Func;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.rule.Rule;
import com.home.main.rule.RuleBase;
import com.home.main.variable.Variable;

public interface RuleSrvice {
	
	public RuleBase getAllRules();
	
	public void createFunc(Func func);
	public void createFuzzySet(FuzzySet fs);
	public void createVariable(Variable v);
	public void createRule(Rule rule);
	
	

}
