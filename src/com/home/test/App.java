package com.home.test;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.db.entities.ExprStateDO;
import com.home.main.db.entities.ExpressionDO;
import com.home.main.db.entities.FuncDO;
import com.home.main.db.entities.FuzzySetDO;
import com.home.main.db.entities.StatementDO;
import com.home.main.db.entities.VariableDO;
import com.home.main.rule.RuleBase;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Fuzzy");
//		EntityManager em = emf.createEntityManager();
//		
//		em.getTransaction().begin();
//		em.persist(b);
//		//em.persist(rule);
//		//em.persist(f1);
//		em.flush();
//		em.getTransaction().commit();
		
		RuleSrvice rs = new RuleServiceImpl();
		RuleBase rb = rs.getAllRules();
		System.out.println(rb.getNumRules());
	}

}
