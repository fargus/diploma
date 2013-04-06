package com.home.main.db.dao;

import java.util.List;

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
	
	public List<Statement> getAllStatements();
	public List<Func> getAllFunc();
	public List<FuzzySet> getAllFuzzySet();
	public List<Variable> getAllVariable();
	public List<Condition> getAllCondition();
	public List<Conclusion> getAllConclusion();
	public List<Rule> getAllRule();
	
	public void createFunc(Func func);
	public void createFuzzySet(FuzzySet fs);
	public void createVariable(Variable v);
	public void createCondition(Condition cond);
	public void createConclusion(Conclusion conc);
	public void createRule(Rule rule);
	
	

}
