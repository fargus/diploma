package com.home.test;

import java.util.HashSet;
import java.util.Set;

import com.home.main.db.dao.CommonDAO;
import com.home.main.db.dao.CommonDAOImpl;
import com.home.main.db.entities.ExpressionDO;
import com.home.main.db.entities.FuncDO;
import com.home.main.db.entities.FuzzySetDO;
import com.home.main.db.entities.RuleDO;
import com.home.main.db.entities.StatementDO;
import com.home.main.db.entities.VariableDO;
import com.home.main.func.FuncType;

public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CommonDAO c = new CommonDAOImpl();
		
		FuncDO func1 = new FuncDO();
		func1.setA(1.);
		func1.setB(4.);
		func1.setType(FuncType.LINEUP.getTypeCode());
		
		FuncDO func2 = new FuncDO();
		func2.setA(3.);
		func2.setB(6.);
		func2.setType(FuncType.LINEDOWN.getTypeCode());
		
		//c.create(func1);
		//c.create(func2);
		
		FuzzySetDO f1 = new FuzzySetDO();
		f1.setName("f1");
		f1.setFunc(func1);
		
		FuzzySetDO f2 = new FuzzySetDO();
		f2.setName("f2");
		f2.setFunc(func2);
		
		//c.create(f1);
		//c.create(f2);
		
		Set<FuzzySetDO> terms = new HashSet<FuzzySetDO>();
		terms.add(f1);
		terms.add(f2);
		
		VariableDO var1 = new VariableDO();
		var1.setMax(100);
		var1.setMin(0);
		var1.setName("VAR1");
		var1.setTerms(terms);
		
		//c.create(var1);
		
		StatementDO state1 = new StatementDO();
		state1.setVariable(var1);
		state1.setFuzzyset(f1);
		
		//state1 = c.create(state1);
		
		Set<StatementDO> stt1 = new HashSet<StatementDO>();
		stt1.add(state1);
		
		ExpressionDO cond = new ExpressionDO();
		cond.setOp(1);
		cond.setStatement(stt1);
		
		//c.create(cond);
		
		RuleDO rule = new RuleDO();
		rule.setConc(cond);
		rule.setCond(cond);
		
		c.create(rule);
		
		System.out.println(var1.getId());
		
/*		
		
		c.create(f1);
		
		
		func1.setTerms(terms);
		
		FuncDO func2 = new FuncDO();
		func2.setA(3.);
		func2.setB(6.);
		func2.setType(FuncType.LINEDOWN.getTypeCode());
		
		FuzzySetDO f2 = new FuzzySetDO();
		f2.setName("f2");
		f2.setFunc(func2);
		
		Set<FuzzySetDO> terms2 = new HashSet<FuzzySetDO>();
		terms2.add(f2);
		func2.setTerms(terms2);
		
		RuleDO rule = new RuleDO();
		
		ExpressionDO cond = new ExpressionDO();
		ExpressionDO conc = new ExpressionDO();
		
		StatementDO state1 = new StatementDO();
		StatementDO state2 = new StatementDO();
		
		
		VariableDO var2 = new VariableDO();
		
		
		f1.setVariable(var1);
		var1.setStatement(state1);
		
		var2.setMax(200);
		var2.setMin(1);
		var2.setName("VAR2");
		var2.setTerms(terms2);
		f2.setVariable(var2);
		var2.setStatement(state2);
		
		state1.setVariable(var1);
		state1.setFuzzyset(f1);
		Set<ExpressionDO> es1 = new HashSet<ExpressionDO>();
		es1.add(cond);
		state1.setExpression(es1);
		
		state2.setVariable(var2);
		state2.setFuzzyset(f2);
		Set<ExpressionDO> es2 = new HashSet<ExpressionDO>();
		es2.add(conc);
		state2.setExpression(es2);
		
		state2.setWeight(0.6);
		
		Set<StatementDO> stt1 = new HashSet<StatementDO>();
		stt1.add(state1);
		
		Set<StatementDO> stt2 = new HashSet<StatementDO>();
		stt2.add(state2);
		
		cond.setOp(1);
		cond.setStatement(stt1);
		
		conc.setOp(1);
		conc.setStatement(stt2);

		
		rule.setConc(conc);
		rule.setCond(cond);
*/		
		//
//		StatementDO s1 = new StatementDO();
//		ExpressionDO e1 = new ExpressionDO();
//		ExprStateDO exs1 = new ExprStateDO();
//		exs1.setExpression(e1);
//		exs1.setStatement(state1);
		//
		
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Fuzzy");
//		EntityManager em = emf.createEntityManager();
//		
//		em.getTransaction().begin();
//		//em.persist(cond);
//		em.persist(rule);
//		//em.persist(exs1);
//		em.flush();
//		em.getTransaction().commit();

	}

}
