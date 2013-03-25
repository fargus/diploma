package com.home.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.home.main.algorithm.Algorithm;
import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.func.Func;
import com.home.main.func.FuncType;
import com.home.main.func.FuncUtil;
import com.home.main.fuzzyset.FuzzySet;
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
		RuleSrvice rs = new RuleServiceImpl();
		
		Variable v1 = new Variable("water temp", 0, 100);
		
		FuzzySet fs1 = new FuzzySetImpl("cold", FuncUtil.createFunc(10, 30, FuncType.Z));
		FuzzySet fs2 = new FuzzySetImpl("cool", FuncUtil.createFunc(20, 35, 50, FuncType.TRIANGLE));
		FuzzySet fs3 = new FuzzySetImpl("warm", FuncUtil.createFunc(40, 50, 60, FuncType.TRIANGLE));
		FuzzySet fs4 = new FuzzySetImpl("not hot", FuncUtil.createFunc(50, 60, 70, FuncType.TRIANGLE));
		FuzzySet fs5 = new FuzzySetImpl("hot", FuncUtil.createFunc(60, 70, FuncType.S));
		
//		rs.createFuzzySet(fs1);
//		System.out.println(fs1.getId());
//		rs.createFuzzySet(fs2);
//		System.out.println(fs2.getId());
//		rs.createFuzzySet(fs3);
//		System.out.println(fs3.getId());
//		rs.createFuzzySet(fs4);
//		System.out.println(fs4.getId());
//		rs.createFuzzySet(fs5);
//		System.out.println(fs5.getId());
//		
		v1.addFuzzySet(fs1);
		v1.addFuzzySet(fs2);
		v1.addFuzzySet(fs3);
		v1.addFuzzySet(fs4);
		v1.addFuzzySet(fs5);
		
		//rs.createVariable(v1);
		
		Variable v2 = new Variable("angle", -90, 90);
		
		FuzzySet fs6 = new FuzzySetImpl("full left", FuncUtil.createFunc(-72, -36, FuncType.Z));
		FuzzySet fs7 = new FuzzySetImpl("left", FuncUtil.createFunc(-54, -27, 0, FuncType.TRIANGLE));
		FuzzySet fs8 = new FuzzySetImpl("center", FuncUtil.createFunc(-18, 0, 18, FuncType.TRIANGLE));
		FuzzySet fs9 = new FuzzySetImpl("right", FuncUtil.createFunc(0, 27, 54, FuncType.TRIANGLE));
		FuzzySet fs10 = new FuzzySetImpl("full right", FuncUtil.createFunc(36, 72, FuncType.S));
		
		v2.addFuzzySet(fs6);
		v2.addFuzzySet(fs7);
		v2.addFuzzySet(fs8);
		v2.addFuzzySet(fs9);
		v2.addFuzzySet(fs10);
		
		//rs.createVariable(v2);
		
		Rule r1 = new Rule(1, new Condition(fs5, v1), new Conclusion(fs10, v2));
		Rule r2 = new Rule(2, new Condition(fs4, v1), new Conclusion(fs9, v2));
		Rule r3 = new Rule(3, new Condition(fs3, v1), new Conclusion(fs8, v2));
		Rule r4 = new Rule(4, new Condition(fs2, v1), new Conclusion(fs7, v2));
		Rule r5 = new Rule(5, new Condition(fs1, v1), new Conclusion(fs6, v2));
		
		
		rs.createRule(r1);
		rs.createRule(r2);
		rs.createRule(r3);
		rs.createRule(r4);
		rs.createRule(r5);
		RuleBase rb1 = rs.getAllRules();
		System.out.println(rb1.getNumRules());
		
		//System.out.println(rs.(1).toString());
		
		for (Rule r : rb1.getRules()){
			System.out.println(r.toString());
		}
		
		Map<Integer, Double> inputVal = new HashMap<Integer, Double>();
		Random rnd = new Random();
		
		inputVal.put(1, rnd.nextDouble()*100);
		
		Algorithm a = new Algorithm();
		a.setRuleBase(rb1);
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
