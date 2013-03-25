package com.home.main.rule;

import com.home.main.db.entities.StatementDO;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.variable.Modificator;
import com.home.main.variable.Operator;
import com.home.main.variable.Statement;
import com.home.main.variable.Variable;

public class Conclusion extends Statement {
	
	private double weight = 1;
	private Operator operator = Operator.AND;

	public Conclusion(Integer id, FuzzySet term, Variable var, Modificator mod) {
		super(id, term, var, mod);
	}

	public Conclusion(Integer id, FuzzySet term, Variable var) {
		super(id, term, var);
	}
	
	public Conclusion(FuzzySet term, Variable var, Modificator mod) {
		super(null, term, var, mod);
	}

	public Conclusion(FuzzySet term, Variable var) {
		super(null, term, var);
	}
	
	public Conclusion(Statement stat){
		super(stat.getId(), stat.getTerm(), stat.getVar(), stat.getMod());
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	@Override
	public StatementDO getSDO(){
		StatementDO s = super.getSDO();
		s.setWeight(weight);
		return s;
	}
	
}
