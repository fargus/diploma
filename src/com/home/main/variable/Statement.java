package com.home.main.variable;

import com.home.main.fuzzyset.FuzzySet;

public class Statement implements FuzzySet {
	
	private int id;

	private FuzzySet term;
	private Variable var;
	private Modificator mod;
	
	public Statement(int id, FuzzySet term, Variable var){
		this(id, term, var, null);
	}
	
	public Statement(int id, FuzzySet term, Variable var, Modificator mod){
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

	public int getId() {
		return id;
	}

}
