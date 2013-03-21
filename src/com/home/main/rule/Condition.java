package com.home.main.rule;

import com.home.main.fuzzyset.FuzzySet;
import com.home.main.variable.Modificator;
import com.home.main.variable.Operator;
import com.home.main.variable.Statement;
import com.home.main.variable.Variable;

public class Condition extends Statement {
	
	private String operator = Operator.AND;

	public Condition(int id, FuzzySet term, Variable var, Modificator mod) {
		super(id, term, var, mod);
	}
	
	public Condition(int id, FuzzySet term, Variable var) {
		super(id, term, var);
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
