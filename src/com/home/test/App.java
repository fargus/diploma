package com.home.test;

import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.func.Func;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.rule.Rule;
import com.home.main.variable.Statement;
import com.home.main.variable.Variable;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Fuzzy");
//		EntityManager em = emf.createEntityManager();
//		
//		//List l = em.createQuery("Select item from FuzzySetDO as item").getResultList();
//		FuzzySetDO f = em.find(FuzzySetDO.class, 1);
//		System.out.println(f.getName());
//		
//		em.getTransaction().begin();
//		em.persist(b);
//		//em.persist(rule);
//		//em.persist(f1);
//		em.flush();
//		em.getTransaction().commit();
		
		RuleSrvice rs = new RuleServiceImpl();
		//RuleBase rb = rs.getAllRules();
		
		for(Func f : rs.getAllFunc()){
			System.out.println(f.toString());
		}
		
		for(FuzzySet f : rs.getAllFuzzySet()){
			System.out.println(f.toString());
		}
		
		for(Variable f : rs.getAllVariable()){
			System.out.println(f.toString());
		}
		
		for(Statement f : rs.getAllStatements()){
			System.out.println(f.toString());
		}
		
		for(Condition f : rs.getAllCondition()){
			System.out.println(f.toString());
		}
		
		for(Conclusion f : rs.getAllConclusion()){
			System.out.println(f.toString());
		}
		
		for(Rule f : rs.getAllRule()){
			System.out.println(f.toString());
		}
		
		//System.out.println(rs.getAllFunc().get(0)..size());
	}

}
