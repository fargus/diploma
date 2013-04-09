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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mod == null) ? 0 : mod.hashCode());
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		result = prime * result + ((var == null) ? 0 : var.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Statement other = (Statement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mod != other.mod)
			return false;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		if (var == null) {
			if (other.var != null)
				return false;
		} else if (!var.equals(other.var))
			return false;
		return true;
	}

}
