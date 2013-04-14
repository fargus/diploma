package com.home.main.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.home.main.fuzzyset.ActivatedFuzzySet;
import com.home.main.fuzzyset.UnionOfFuzzySet;
import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.rule.Rule;
import com.home.main.variable.Operator;
import com.home.main.variable.Variable;

public class Algorithm2 {
	
	private static final Logger log = Logger.getLogger(Algorithm2.class);
	
	private Rule[] rules;
	private List<Variable> outputVars;
	private double[] outputVals;
	
	private int numOfOutputVars;

	private AggregationType aggrType;
	private ImplicationType actType;
	private AccumulationType accumType;
	
	public void setRules(Collection<Rule> rules){
		this.rules = rules.toArray(new Rule[rules.size()]);
		this.outputVars = new ArrayList<Variable>();
		for (Rule r : rules){
			for(Conclusion c : r.getConclusions()){
				Variable v = c.getVar();
				if (!outputVars.contains(v)){
					outputVars.add(v);
				}
			}
		}
		
		this.numOfOutputVars = outputVars.size();
		this.outputVals = new double[numOfOutputVars];
	}
	
	public void setAggrType(AggregationType aggrType) {
		this.aggrType = aggrType;
	}

	public void setActType(ImplicationType actType) {
		this.actType = actType;
	}

	public void setAccumType(AccumulationType accumType) {
		this.accumType = accumType;
	}
	
	public double[] run(List<Double> input) throws Exception {
		UnionOfFuzzySet[] unions =new UnionOfFuzzySet[numOfOutputVars];
		for(int i = 0; i< numOfOutputVars; i++){
			unions[i] = new UnionOfFuzzySet(accumType);
		}
		
		for (int i = 0; i < rules.length; i++) {
			Operator op = rules[i].getConcO();
			double aggrCond = (op == Operator.AND)?1:0;
			for(Condition c : rules[i].getConditions()){
				aggrCond = aggrType.getValue(aggrCond, c.getValue(input.get(c.getVar().getId())), op);
			}
			
			for (Conclusion c : rules[i].getConclusions()) {
				unions[outputVars.indexOf(c.getVar())].addFuzzySet(new ActivatedFuzzySet(actType.getValue(c.getWeight(), aggrCond), c.getTerm()));
			}
		}

		for(int i = 0; i<numOfOutputVars; i++){
			outputVals[i] = integral2(outputVars.get(i).getMin(), outputVars.get(i).getMax(), unions[i]);
		}
		return outputVals;
	}
	
	private double integral2(double min, double max, UnionOfFuzzySet u) {
		double resultUp = 0;
		double resultDown = 0;
		for (double i = min; i <= max; i += 1) {
				resultUp += i * u.getValue(i);
				resultDown += u.getValue(i);
		}
		return resultUp/resultDown;
	}
}
