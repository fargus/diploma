package com.home.main.db.dao;

import java.util.Map;

import com.home.main.func.Func;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.rule.Rule;
import com.home.main.rule.RuleBase;
import com.home.main.variable.Statement;
import com.home.main.variable.Variable;

public interface RuleSrvice {
	
	public RuleBase getRuleBase();
	
	public Map<Integer, Statement> getAllStatements();
	public Map<Integer, Func> getAllFunc();
	public Map<Integer, FuzzySet> getAllFuzzySet();
	public Map<Integer, Variable> getAllVariable();
	public Map<Integer, Condition> getAllCondition();
	public Map<Integer, Conclusion> getAllConclusion();
	public Map<Integer, Rule> getAllRule();
	
	public void createFunc(Func func);
	public void createFuzzySet(FuzzySet fs);
	public void createVariable(Variable v);
	public void createCondition(Condition cond);
	public void createConclusion(Conclusion conc);
	public void createRule(Rule rule);
	
	

}
