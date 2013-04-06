package com.home.main.fuzzyset;

import com.home.main.db.entities.FuzzySetDO;
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
		return new FuzzySetDO(id, name, null, func.getDO(), null);
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

}
