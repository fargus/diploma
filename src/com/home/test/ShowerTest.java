package com.home.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.home.main.Algorithm;
import com.home.main.func.Func;
import com.home.main.func.FuncType;
import com.home.main.func.FuncUtil;
import com.home.main.fuzzyset.FuzzySetImpl;
import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.rule.Rule;
import com.home.main.rule.RuleBase;
import com.home.main.variable.Variable;

public class ShowerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Variable v1 = new Variable(1, "water temp", 0, 100);
		
		v1.addFuzzySet((new FuzzySetImpl(1, "cold", FuncUtil.createFunc(10, 30, FuncType.Z))));
		v1.addFuzzySet((new FuzzySetImpl(2, "cool", FuncUtil.createFunc(20, 35, 50, FuncType.TRIANGLE))));
		v1.addFuzzySet((new FuzzySetImpl(3, "warm", FuncUtil.createFunc(40, 50, 60, FuncType.TRIANGLE))));
		v1.addFuzzySet((new FuzzySetImpl(4, "not hot", FuncUtil.createFunc(50, 60, 70, FuncType.TRIANGLE))));
		v1.addFuzzySet((new FuzzySetImpl(5, "hot", FuncUtil.createFunc(60, 70, FuncType.S))));
		
		Variable v2 = new Variable(2, "angle", -90, 90);
		
		v2.addFuzzySet((new FuzzySetImpl(6, "full left", FuncUtil.createFunc(-72, -36, FuncType.Z))));
		v2.addFuzzySet((new FuzzySetImpl(7, "left", FuncUtil.createFunc(-54, -27, 0, FuncType.TRIANGLE))));
		v2.addFuzzySet((new FuzzySetImpl(8, "center", FuncUtil.createFunc(-18, 0, 18, FuncType.TRIANGLE))));
		v2.addFuzzySet((new FuzzySetImpl(9, "right", FuncUtil.createFunc(0, 27, 54, FuncType.TRIANGLE))));
		v2.addFuzzySet((new FuzzySetImpl(10, "full right", FuncUtil.createFunc(36, 72, FuncType.S))));
		
		RuleBase rb = new RuleBase();
		
		Rule r1 = new Rule(1, new Condition(1, v1.getFuzzySet(5), v1), new Conclusion(2, v2.getFuzzySet(10), v2));
		Rule r2 = new Rule(2, new Condition(3, v1.getFuzzySet(4), v1), new Conclusion(4, v2.getFuzzySet(9), v2));
		Rule r3 = new Rule(3, new Condition(5, v1.getFuzzySet(3), v1), new Conclusion(6, v2.getFuzzySet(8), v2));
		Rule r4 = new Rule(4, new Condition(7, v1.getFuzzySet(2), v1), new Conclusion(8, v2.getFuzzySet(7), v2));
		Rule r5 = new Rule(5, new Condition(9, v1.getFuzzySet(1), v1), new Conclusion(10, v2.getFuzzySet(6), v2));
		
		rb.addRule(r1).addRule(r2).addRule(r3).addRule(r4).addRule(r5);
		
		Map<Integer, Double> inputVal = new HashMap<Integer, Double>();
		Random rnd = new Random();
		
		inputVal.put(1, rnd.nextDouble()*100);
		
		Algorithm a = new Algorithm();
		a.setRuleBase(rb);
		try {
			for(int i = 0; i<=100;i++){
				inputVal.put(1, i+.0);
				Map<Integer, Double> output = a.run(inputVal);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
