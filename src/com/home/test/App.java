package com.home.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
//		CommonDAO dao = new CommonDAOImpl();
	//	VariableDO f = dao.findById(VariableDO.class, new Integer(1));
	//	System.out.println(f.getName());
	//	dao.findAll(VariableDO.class);
		
//		VariableDO f = dao.findById(VariableDO.class, new Integer(1));
//		f.getTerms().remove(f.getTerms().iterator().next());
//		dao.update(f);
//		f = dao.findById(VariableDO.class, new Integer(1));
//		System.out.println(f.getTerms().size());
		
//		ExpressionDO e = dao.findById(ExpressionDO.class, 2);
//		StatementDO s = dao.findById(StatementDO.class, 2);
//		e.addStatement(s);
//		dao.update(e);
		
//		
//		em.getTransaction().begin();
//		em.persist(b);
//		//em.persist(rule);
//		//em.persist(f1);
//		em.flush();
//		em.getTransaction().commit();
		
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
		
		Map<Integer, Condition> conds = rs.getAllCondition();
		Map<Integer, Conclusion> concs = rs.getAllConclusion();
		
		for(int a1 = 1; a1 <=2; a1++){
			for(int a2 = 3; a2 <=4; a2++){
				for(int a3 = 5; a3 <=6; a3++){
					for(int a4 = 7; a4 <=8; a4++){
						for(int a5 = 9; a5 <=10; a5++){
							for(int a6 = 11; a6 <=12; a6++){
								int conc;
								if (a1==2 && a2==4 && a3==6 && a4==8 && a5==10 && a6==12){
									conc = 13;
								}else{
									conc = 14;
								}
								List<Condition> cd = new ArrayList<Condition>(6);
								List<Conclusion> cc = new ArrayList<Conclusion>(1);
								cd.add(conds.get(a1));
								cd.add(conds.get(a2));
								cd.add(conds.get(a3));
								cd.add(conds.get(a4));
								cd.add(conds.get(a5));
								cd.add(conds.get(a6));
								cc.add(concs.get(conc));
								Rule r = new Rule(cd, cc);
								rs.createRule(r);
								System.out.println(r.toString());
							}
						}
					}
				}
			}
		}
		//System.out.println(rs.getAllFunc().get(0)..size());
	}

}
