package com.home.main.rule;

import com.home.main.fuzzyset.FuzzySet;
import com.home.main.variable.Modificator;
import com.home.main.variable.Operator;
import com.home.main.variable.Statement;
import com.home.main.variable.Variable;

public class Condition extends Statement {
	
	private Operator operator = Operator.AND;

	public Condition(Integer id, FuzzySet term, Variable var, Modificator mod) {
		super(id, term, var, mod);
	}
	
	public Condition(FuzzySet term, Variable var, Modificator mod) {
		super(null, term, var, mod);
	}
	
	public Condition(Integer id, FuzzySet term, Variable var) {
		super(id, term, var);
	}
	
	public Condition(FuzzySet term, Variable var) {
		super(null, term, var);
	}
	
	public Condition(Statement stat){
		super(stat.getId(), stat.getTerm(), stat.getVar(), stat.getMod());
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public String toString(){
		return "Condition"+super.toString().substring(9);
	}

}
