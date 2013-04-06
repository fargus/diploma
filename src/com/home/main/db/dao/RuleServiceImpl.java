package com.home.main.db.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityExistsException;

import com.home.main.db.entities.FuncDO;
import com.home.main.db.entities.FuzzySetDO;
import com.home.main.db.entities.RuleDO;
import com.home.main.db.entities.StatementDO;
import com.home.main.db.entities.VariableDO;
import com.home.main.func.Func;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.rule.Rule;
import com.home.main.rule.RuleBase;
import com.home.main.variable.Statement;
import com.home.main.variable.Variable;

public class RuleServiceImpl implements RuleSrvice {

	CommonDAO dao = new CommonDAOImpl();
	

	@Override
	public void createFunc(Func func) {
		FuncDO f = dao.create(func.getDO());
		func.setId(f.getId());
	}

	@Override
	public void createFuzzySet(FuzzySet fs) {
		
		FuzzySetDO f = fs.getDO();
		checkFuzzySet(f);
		
		f = dao.create(f);
		fs.setId(f.getId());
	}
	
	private void checkFuzzySet(FuzzySetDO f){
		Integer func_id = f.getFunc().getId();
		if (func_id != null){
			FuncDO funcDO = dao.findById(FuncDO.class, func_id);
			if (funcDO != null){
				f.setFunc(funcDO);
			}else{
				f.getFunc().setId(null);
			}
		}
	}

	@Override
	public void createVariable(Variable v) {
	
		VariableDO var = v.getDO();
		checkVariable(var);
		
		dao.create(var);
		v.setId(var.getId());
	}
	
	private void checkVariable(VariableDO var){
		Set<FuzzySetDO> terms = new HashSet<FuzzySetDO>();
		for(FuzzySetDO fs : var.getTerms()){
			Integer id = fs.getId();
			if (id != null){
				FuzzySetDO temp = dao.findById(FuzzySetDO.class, id);
				if (temp != null){
					terms.add(temp);
					continue;
				}else{
					fs.setId(null);
				}
			}
			checkFuzzySet(fs);
			terms.add(fs);
		}
		var.setTerms(terms);
	}

	@Override
	public void createRule(Rule rule) {
		RuleDO r = rule.getDO();
		
		Set<StatementDO> temp_cond = new HashSet<StatementDO>();
		Set<StatementDO> temp_conc = new HashSet<StatementDO>();
		
		for(StatementDO cond : r.getCond().getStatement()){
			Integer id = cond.getId();
			if (id == null) {
				throw new EntityExistsException("Save condition before seva rule!");
			}
			
			temp_cond.add(dao.findById(StatementDO.class, id));
		}
		
		for(StatementDO conc : r.getConc().getStatement()){
			Integer id = conc.getId();
			if (id == null) {
				throw new EntityExistsException("Save conclusion before seva rule!");
			}
			
			temp_conc.add(dao.findById(StatementDO.class, id));
		}
		
		r.getCond().setStatement(temp_cond);
		r.getConc().setStatement(temp_conc);
		
		dao.create(r);
		rule.setId(r.getId());
	}

	@Override
	public RuleBase getRuleBase() {
		RuleBase rb = new RuleBase();
		for(RuleDO r : dao.findAll(RuleDO.class)){
			rb.addRule(r.getDTO());
		}
		return rb;
	}

	@Override
	public void createCondition(Condition cond) {
		StatementDO sdo = cond.getSDO();
		checkStatement(sdo);	
		
		dao.create(sdo);
		cond.setId(sdo.getId());
	}

	@Override
	public void createConclusion(Conclusion conc) {
		StatementDO sdo = conc.getSDO();
		checkStatement(sdo);
		
		dao.create(sdo);
		conc.setId(sdo.getId());
	}
	
	private void checkStatement(StatementDO sdo){
		Integer var_id = sdo.getVariable().getId();
		if (var_id != null){
			VariableDO var = dao.findById(VariableDO.class, var_id);
			if (var != null){
				sdo.setVariable(var);
			}else{
				sdo.getVariable().setId(null);
				checkVariable(sdo.getVariable());
			}
		}else{
			checkVariable(sdo.getVariable());
		}
		
		Integer fs_id = sdo.getFuzzyset().getId();
		if (fs_id != null){
			FuzzySetDO fs = dao.findById(FuzzySetDO.class, fs_id);
			if (fs != null){
				sdo.setFuzzyset(fs);
			}else{
				sdo.getFuzzyset().setId(null);
				checkFuzzySet(sdo.getFuzzyset());
			}
		}else{
			checkFuzzySet(sdo.getFuzzyset());
		}
		
		VariableDO var = sdo.getVariable();
		FuzzySetDO fs = sdo.getFuzzyset();
		System.out.println("Var id = "+var.getId());
		System.out.println("Term id = "+ fs.getId());

	}

	@Override
	public List<Statement> getAllStatements() {
		List<Statement> result = new ArrayList<Statement>();
		for(StatementDO s : dao.findAll(StatementDO.class)){
			result.add(s.getDTO());
		}
		return result;
	}

	@Override
	public List<Func> getAllFunc() {
		List<Func> result = new ArrayList<Func>();
		for(FuncDO f : dao.findAll(FuncDO.class)){
			result.add(f.getDTO());
		}
		
		return result;
	}

	@Override
	public List<FuzzySet> getAllFuzzySet() {
		List<FuzzySet> result = new ArrayList<FuzzySet>();
		for(FuzzySetDO f : dao.findAll(FuzzySetDO.class)){
			result.add(f.getDTO());
		}
		
		return result;
	}

	@Override
	public List<Variable> getAllVariable() {
		List<Variable> result = new ArrayList<Variable>();
		for(VariableDO v : dao.findAll(VariableDO.class)){
			result.add(v.getDTO());
		}
		
		return result;
	}

	@Override
	public List<Condition> getAllCondition() {
		List<Condition> result = new ArrayList<Condition>();
		for(StatementDO s : dao.findAll(StatementDO.class)){
			if (s.getWeight() == null){
				Statement st = s.getDTO();
				result.add(new Condition(st.getId(), st.getTerm(), st.getVar(), st.getMod()));
			}
		}
		return result;
	}

	@Override
	public List<Conclusion> getAllConclusion() {
		List<Conclusion> result = new ArrayList<Conclusion>();
		for(StatementDO s : dao.findAll(StatementDO.class)){
			if (s.getWeight() != null){
				Statement st = s.getDTO();
				result.add(new Conclusion(st.getId(), st.getTerm(), st.getVar(), st.getMod(), s.getWeight()));
			}
		}
		return result;
	}

	@Override
	public List<Rule> getAllRule() {
		List<Rule> result = new ArrayList<Rule>();
		for(RuleDO r : dao.findAll(RuleDO.class)){
			result.add(r.getDTO());
		}
		return result;
	}

}
