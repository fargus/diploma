package com.home.main.variable;

import com.home.main.db.entities.FuzzySetDO;
import com.home.main.db.entities.StatementDO;
import com.home.main.func.Func;
import com.home.main.fuzzyset.FuzzySet;

public class Statement implements FuzzySet {
	
	private Integer id;

	private FuzzySet term;
	private Variable var;
	private Modificator mod;
	
	public Statement(Integer id, FuzzySet term, Variable var){
		this(id, term, var, null);
	}
	
	public Statement(Integer id, FuzzySet term, Variable var, Modificator mod){
		this.id=id;
		this.term=term;
		this.var=var;
		this.mod = mod;
	}

	@Override
	public String getName() {
		StringBuilder sb = new StringBuilder();
		sb.append(var.getName());
		sb.append(" IS ");
		sb.append((mod != null)?mod.getName()+" ":"");
		sb.append(term.getName());
		return sb.toString();
	}

	@Override
	public double getValue(double d) {
		return term.getValue(d);
	}
	
	
	public FuzzySet getTerm() {
		return term;
	}

	public void setTerm(FuzzySet term) {
		this.term = term;
	}

	public Variable getVar() {
		return var;
	}

	public void setVar(Variable var) {
		this.var = var;
	}

	public Modificator getMod() {
		return mod;
	}

	public void setMod(Modificator mod) {
		this.mod = mod;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public FuzzySetDO getDO() {
		return term.getDO();
	}
	
	public StatementDO getSDO(){
		return new StatementDO(id, var.getDO(), term.getDO(), (mod != null)?mod.getDO():null, null, null);
	}

	@Override
	public void setId(Integer id) {
		this.id=id;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Statement: ");
		if (id != null){
			sb.append("ID=["+id+"] ");
		}
		sb.append("Variable=["+var.getId()+":"+var.getName()+"]");
		sb.append(" IS ");
		sb.append("Mod=["+((mod != null)?mod.getName():"")+"] ");
		sb.append("Term=["+term.getId()+":"+term.getName()+"]");
		return sb.toString();
	}

	@Override
	public Func getFunc() {
		return term.getFunc();
	}

}
