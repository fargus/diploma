package com.home.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.home.main.db.dao.CommonDAO;
import com.home.main.db.dao.CommonDAOImpl;
import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.db.entities.ExpressionDO;
import com.home.main.db.entities.FuzzySetDO;
import com.home.main.db.entities.RuleDO;
import com.home.main.db.entities.StatementDO;
import com.home.main.db.entities.VariableDO;
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
		
		//List l = em.createQuery("Select item from FuzzySetDO as item").getResultList();
		CommonDAO dao = new CommonDAOImpl();
	//	VariableDO f = dao.findById(VariableDO.class, new Integer(1));
	//	System.out.println(f.getName());
	//	dao.findAll(VariableDO.class);
		
//		VariableDO f = dao.findById(VariableDO.class, new Integer(1));
//		f.getTerms().remove(f.getTerms().iterator().next());
//		dao.update(f);
//		f = dao.findById(VariableDO.class, new Integer(1));
//		System.out.println(f.getTerms().size());
		
		ExpressionDO e = dao.findById(ExpressionDO.class, 2);
		StatementDO s = dao.findById(StatementDO.class, 2);
		e.addStatement(s);
		dao.update(e);
		
//		
//		em.getTransaction().begin();
//		em.persist(b);
//		//em.persist(rule);
//		//em.persist(f1);
//		em.flush();
//		em.getTransaction().commit();
		/*
		RuleSrvice rs = new RuleServiceImpl();
		//RuleBase rb = rs.getAllRules();
		
		for(Func f : rs.getAllFunc().values()){
			System.out.println(f.toString());
		}
		
		for(FuzzySet f : rs.getAllFuzzySet().values()){
			System.out.println(f.toString());
		}
		
		for(Variable f : rs.getAllVariable().values()){
			System.out.println(f.toString());
		}
		
		for(Statement f : rs.getAllStatements().values()){
			System.out.println(f.toString());
		}
		
		for(Condition f : rs.getAllCondition().values()){
			System.out.println(f.toString());
		}
		
		for(Conclusion f : rs.getAllConclusion().values()){
			System.out.println(f.toString());
		}
		
		for(Rule f : rs.getAllRule().values()){
			System.out.println(f.toString());
		}
		*/
		//System.out.println(rs.getAllFunc().get(0)..size());
	}

}
