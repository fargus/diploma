package com.home.main.fuzzyset;

import com.home.main.db.entities.FuzzySetDO;
import com.home.main.func.Func;

public class ActivatedFuzzySet implements FuzzySet{

	private double truthDegree = 1;
	private FuzzySet fuzzySet; 
	
	public ActivatedFuzzySet(double truthDegree, FuzzySet fuzzySet) {
		this.fuzzySet = fuzzySet;
		this.truthDegree = truthDegree;
	}
	
	public void setTruthDegree(double truthDegree){
		this.truthDegree = truthDegree;
	}

	@Override
	public String getName() {
		return fuzzySet.getName();
	}

	@Override
	public double getValue(double d) {
		return Math.min(truthDegree, fuzzySet.getValue(d)); // can be changed
	}

	@Override
	public Integer getId() {
		return fuzzySet.getId();
	}

	@Override
	public FuzzySetDO getDO() {
		return fuzzySet.getDO();
	}

	@Override
	public void setId(Integer id) {
		this.fuzzySet.setId(id);
	}

	@Override
	public Func getFunc() {
		return fuzzySet.getFunc();
	}
	
	public String toString(){
		return fuzzySet.toString()+" "+ truthDegree;
	}

}
