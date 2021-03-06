package com.home.main.db.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityExistsException;

import com.home.main.db.entities.ExpressionDO;
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
		
	}

	@Override
	public Map<Integer, Statement> getAllStatements() {
		Map<Integer, Statement> result = new HashMap<Integer, Statement>();
		for(StatementDO s : dao.findAll(StatementDO.class)){
			result.put(s.getId(), s.getDTO());
		}
		return result;
	}

	@Override
	public Map<Integer, Func> getAllFunc() {
		Map<Integer, Func> result = new HashMap<Integer, Func>();
		for(FuncDO f : dao.findAll(FuncDO.class)){
			result.put(f.getId(), f.getDTO());
		}
		
		return result;
	}

	@Override
	public Map<Integer, FuzzySet> getAllFuzzySet() {
		Map<Integer, FuzzySet> result = new HashMap<Integer, FuzzySet>();
		for(FuzzySetDO f : dao.findAll(FuzzySetDO.class)){
			result.put(f.getId(), f.getDTO());
		}
		
		return result;
	}

	@Override
	public Map<Integer, Variable> getAllVariable() {
		Map<Integer, Variable> result = new HashMap<Integer, Variable>();
		for(VariableDO v : dao.findAll(VariableDO.class)){
			result.put(v.getId(), v.getDTO());
		}
		
		return result;
	}

	@Override
	public Map<Integer, Condition> getAllCondition() {
		Map<Integer, Condition> result = new HashMap<Integer, Condition>();
		for(StatementDO s : dao.findAll(StatementDO.class)){
			if (s.getWeight() == null){
				Statement st = s.getDTO();
				result.put(st.getId(), new Condition(st.getId(), st.getTerm(), st.getVar(), st.getMod()));
			}
		}
		return result;
	}

	@Override
	public Map<Integer,  Conclusion> getAllConclusion() {
		Map<Integer, Conclusion> result = new HashMap<Integer, Conclusion>();
		for(StatementDO s : dao.findAll(StatementDO.class)){
			if (s.getWeight() != null){
				Statement st = s.getDTO();
				result.put(st.getId(), new Conclusion(st.getId(), st.getTerm(), st.getVar(), st.getMod(), s.getWeight()));
			}
		}
		return result;
	}

	@Override
	public Map<Integer, Rule> getAllRule() {
		Map<Integer, Rule> result = new HashMap<Integer, Rule>();
		for(RuleDO r : dao.findAll(RuleDO.class)){
			result.put(r.getId(), r.getDTO());
		}
		return result;
	}

	@Override
	public void updateFunc(Func func) {
		FuncDO f = dao.findById(FuncDO.class, func.getId());
		f.setA(func.getA());
		f.setB(func.getB());
		f.setC(func.getC());
		f.setD(func.getD());
		f.setType(func.getType().getTypeCode());
		dao.update(f);
	}

	@Override
	public void updateFuzzySet(FuzzySet fs) {
		FuzzySetDO f = dao.findById(FuzzySetDO.class, fs.getId());
		f.setName(fs.getName());
		f.setFunc(fs.getFunc().getDO());
		dao.update(f);
	}

	@Override
	public void updateVariable(Variable v) {
		VariableDO var = dao.findById(VariableDO.class, v.getId());
		var.setMin(v.getMin());
		var.setMax(v.getMax());
		var.setName(v.getName());
		var.setTerms(v.getDO().getTerms());
		var.setMod(v.getDO().getMod());
		dao.update(var);
	}

	@Override
	public void updateCondition(Condition cond) {
		StatementDO c = dao.findById(StatementDO.class, cond.getId());
		c.setVariable(cond.getVar().getDO());
		c.setFuzzyset(cond.getTerm().getDO());
		dao.update(c);
	}

	@Override
	public void updateConclusion(Conclusion conc) {
		StatementDO c = dao.findById(StatementDO.class, conc.getId());
		c.setVariable(conc.getVar().getDO());
		c.setFuzzyset(conc.getTerm().getDO());
		c.setWeight(conc.getWeight());
		dao.update(c);
	}

	@Override
	public void updateRule(Rule rule) {
		RuleDO r = dao.findById(RuleDO.class, rule.getId());
		ExpressionDO cond = rule.getDO().getCond();
		cond.setId(r.getCond().getId());
		ExpressionDO conc = rule.getDO().getConc();
		conc.setId(r.getConc().getId());
		r.setCond(cond);
		r.setConc(conc);
		dao.update(r);
	}

	@Override
	public void deleteFunc(Func func) {
		dao.delete(FuncDO.class, func.getId());
	}

	@Override
	public void deleteFuzzySet(FuzzySet fs) {
		dao.delete(FuzzySetDO.class, fs.getId());
	}

	@Override
	public void deleteVariable(Variable v) {
		dao.delete(VariableDO.class, v.getId());
	}

	@Override
	public void deleteCondition(Condition cond) {
		dao.delete(StatementDO.class, cond.getId());
	}

	@Override
	public void deleteConclusion(Conclusion conc) {
		dao.delete(StatementDO.class, conc.getId());
	}

	@Override
	public void deleteRule(Rule rule) {
		dao.delete(RuleDO.class, rule.getId());
	}

}
