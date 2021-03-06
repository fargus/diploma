package com.home.main.fuzzyset;

import java.util.HashSet;
import java.util.Set;

import com.home.main.db.entities.FuzzySetDO;
import com.home.main.db.entities.VariableDO;
import com.home.main.func.Func;

public class FuzzySetImpl implements FuzzySet {

	private Integer id;
	private String name;
	private Func func;
	
	public FuzzySetImpl(Integer id, String name, Func func){
		this.id=id;
		this.name = name;
		this.func = func;
	}
	
	public FuzzySetImpl(String name, Func func){
		this(null, name, func);
	}
	
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getValue(double d) {
		return func.getValue(d);
	}


	@Override
	public Integer getId() {
		return id;
	}
	
	public FuzzySetDO getDO(){
		Set<VariableDO> var = new HashSet<VariableDO>();
		return new FuzzySetDO(id, name, var, func.getDO(), null);
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(id != null){
			sb.append("ID=["+id+"] ");
		}
		sb.append("Name=["+name+"] ");
		sb.append("FUNC_ID=["+func.getId()+"]");
		return sb.toString();
	}

	@Override
	public Func getFunc() {
		return func;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((func == null) ? 0 : func.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		FuzzySetImpl other = (FuzzySetImpl) obj;
		if (func == null) {
			if (other.func != null)
				return false;
		} else if (!func.equals(other.func))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
