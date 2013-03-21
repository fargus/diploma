package com.home.main.fuzzyset;

import com.home.main.func.Func;

public class FuzzySetImpl implements FuzzySet {

	private int id;
	private String name;
	private Func func;
	
	public FuzzySetImpl(int id, String name, Func func){
		this.id=id;
		this.name = name;
		this.func = func;
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
	public int getId() {
		return id;
	}

}
