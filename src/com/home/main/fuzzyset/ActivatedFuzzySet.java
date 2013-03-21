package com.home.main.fuzzyset;

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
	public int getId() {
		return fuzzySet.getId();
	}

}
