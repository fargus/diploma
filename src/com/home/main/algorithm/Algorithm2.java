package com.home.main.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.home.main.fuzzyset.ActivatedFuzzySet;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.fuzzyset.UnionOfFuzzySet;
import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.rule.Rule;
import com.home.main.variable.Operator;
import com.home.main.variable.Variable;

public class Algorithm2 {
	
	private static final Logger log = Logger.getLogger(Algorithm2.class);
	
	private Rule[] rules;
	private Variable outputVar;
	private double[] outputVals;
	
	private int numOfOutputVars;

	private AggregationType aggrType;
	private ImplicationType actType;
	private AccumulationType accumType;
	
	private List<Double> checkPoints = new ArrayList<Double>();
	private double[] cp;

	private UnionOfFuzzySet union =new UnionOfFuzzySet();
	
	public void setRules(Collection<Rule> rules){
		this.rules = rules.toArray(new Rule[rules.size()]);
		this.outputVar = rules.iterator().next().getConclusions().iterator().next().getVar();
		this.numOfOutputVars = 1;

		for(FuzzySet fs : outputVar.getTerms()){
			String name = fs.getName();
			double left;
			double right;
			if (name.equals("edge")){
				left = fs.getFunc().getA();
				right = fs.getFunc().getC();
			}else {
				left = fs.getFunc().getA();
				right = fs.getFunc().getB();
			}
			
			for(double i = left; i<=right; i++){
				System.out.println(i);
				checkPoints.add(i);
			}
		}
		cp = new double[checkPoints.size()];
		int k = 0;
		for (double i : checkPoints){
			cp[k] = i;
			k++;
		}
	}
	
	public void setAggrType(AggregationType aggrType) {
		this.aggrType = aggrType;
	}

	public void setActType(ImplicationType actType) {
		this.actType = actType;
	}

	public void setAccumType(AccumulationType accumType) {
		this.accumType = accumType;
		union.setAccumulationType(accumType);
	}
	
	public double run(double[] input){
		union.clear();
		
		for (int i = 0; i < rules.length; i++) {
			Operator op = rules[i].getConcO();
			double aggrCond = (op == Operator.AND)?1:0;
			for(Condition c : rules[i].getConditions()){
				aggrCond = aggrType.getValue(aggrCond, c.getValue(input[c.getVar().getId()-1]), op);
			}
			
			for (Conclusion c : rules[i].getConclusions()) {
				union.addFuzzySet(new ActivatedFuzzySet(actType.getValue(c.getWeight(), aggrCond), c.getTerm()));
			}
		}

		return integral2(outputVar.getMin(), outputVar.getMax(), union);
	}
	
	private double integral3(double min, double max, UnionOfFuzzySet u) {
		double m = 0;
		double k = 0;
		for (double i : checkPoints) {
				double temp = u.getValue(i);
				if (temp > m){
					m=temp;
					k=i;
				}
		}
		return k;
	}
	
	private double integral2(double min, double max, UnionOfFuzzySet u) {
		double resultUp = 0;
		double resultDown = 0;
		for (int i = 0; i<cp.length; i++) {
			double k = u.getValue(cp[i]);
			resultUp += cp[i] * k;
			resultDown += k;
		}
		return resultUp/resultDown;
	}

	public Variable getOutputVar() {
		return outputVar;
	}
}
