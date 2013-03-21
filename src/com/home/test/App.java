package com.home.test;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.home.main.db.entities.ExprStateDO;
import com.home.main.db.entities.ExpressionDO;
import com.home.main.db.entities.FuncDO;
import com.home.main.db.entities.FuzzySetDO;
import com.home.main.db.entities.StatementDO;
import com.home.main.db.entities.VariableDO;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StatementDO a = new StatementDO();
		ExpressionDO b = new ExpressionDO();
		ExprStateDO a2b = new ExprStateDO();
		
		FuncDO func = new FuncDO();
		func.setA(0.);
		func.setB(100.);
		func.setType(1);
		
		FuzzySetDO f = new FuzzySetDO();
		f.setName("Name");
		
		f.setFunc(func);
		Set<FuzzySetDO> terms = new HashSet<FuzzySetDO>();
		terms.add(f);
		func.setTerms(terms);
		
		VariableDO v = new VariableDO();
		v.setMax(0.);
		v.setMin(100.);
		v.setName("VAR");
		v.setStatement(a);
		v.setTerms(terms);
		
		a.setVariable(v);
		a.setFuzzyset(f);
		a2b.setStatement(a);
		a2b.setExpression(b);
		
		Set<ExprStateDO> set = new HashSet<ExprStateDO>();
		set.add(a2b);
		a.setExprstate(set);
		b.setExprstate(set);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Fuzzy");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(a);
		//em.persist(rule);
		//em.persist(f1);
		em.flush();
		em.getTransaction().commit();

	}

}
