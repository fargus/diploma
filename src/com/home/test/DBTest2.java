package com.home.test;

import java.util.HashSet;
import java.util.Set;

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
import com.home.main.variable.Variable;

public class DBTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RuleSrvice rs = new RuleServiceImpl();
		
		Func f1 = FuncUtil.createFunc(1., 4., FuncType.LINEUP);
		FuzzySet fuz1 = new FuzzySetImpl("Fuzzy1", f1);
		
		Func f2 = FuncUtil.createFunc(3., 6., FuncType.LINEDOWN);
		FuzzySet fuz2 = new FuzzySetImpl("Fuzzy2", f2);
		
		//rs.createFunc(f1);
		//rs.createFunc(f2);
		
		rs.createFuzzySet(fuz1);
		rs.createFuzzySet(fuz2);
		
		Variable var1 = new Variable("VAR1", 0, 100);
		Set<FuzzySet> terms = new HashSet<FuzzySet>();
		terms.add(fuz1);
		terms.add(fuz2);
		
		var1.setTerms(terms);
		
		rs.createVariable(var1);
		
		System.out.println(f1.getId());
		System.out.println(f2.getId());
		System.out.println(fuz1.getId());
		System.out.println(fuz2.getId());
		System.out.println(var1.getId());
		for(FuzzySet fs : var1.getTerms()){
			System.out.println(fs.getId());
		}
		
		Condition cond = new Condition(fuz1, var1);
		Conclusion conc = new Conclusion(fuz2, var1);
		rs.createCondition(cond);
		rs.createConclusion(conc);
		
		System.out.println(cond.getId());
		System.out.println(conc.getId());
		
		Rule r = new Rule(cond, conc);
		rs.createRule(r);
	}

}
