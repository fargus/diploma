package com.home.main.db.dao;

import com.home.main.db.entities.FuncDO;
import com.home.main.db.entities.FuzzySetDO;
import com.home.main.db.entities.RuleDO;
import com.home.main.db.entities.VariableDO;
import com.home.main.func.Func;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.rule.Rule;
import com.home.main.rule.RuleBase;
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
		FuzzySetDO f = dao.create(fs.getDO());
		fs.setId(f.getId());
	}

	@Override
	public void createVariable(Variable v) {
		VariableDO var = dao.create(v.getDO());
		v.setId(var.getId());
	}

	@Override
	public void createRule(Rule rule) {
		dao.create(rule.getDO());
	}

	@Override
	public RuleBase getAllRules() {
		RuleBase rb = new RuleBase();
		for(RuleDO r : dao.findAll(RuleDO.class)){
			rb.addRule(r.getDTO());
		}
		return rb;
	}

}
