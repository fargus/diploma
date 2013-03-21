package com.home.main.variable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.home.main.fuzzyset.FuzzySet;

public class Variable {

	private int id;

	private String name;
	private Map<Integer, FuzzySet> terms;
	private Set<Modificator> modyficators;
	private double min;
	private double max;

	public Variable(int id, String name, double min, double max) {
		this(id, name, null, min, max, null);
		setModyficator(new HashSet<Modificator>());
		setTerms(new HashMap<Integer, FuzzySet>());
	}
	public Variable(int id, String name, Map<Integer, FuzzySet> terms, double min, double max,
			Set<Modificator> modyficators) {
		this.id = id;
		this.name = name;
		this.terms = terms;
		this.modyficators = modyficators;
		this.min = min;
		this.max = max;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Integer, FuzzySet> getTerms() {
		return terms;
	}

	public void setTerms(Map<Integer, FuzzySet> terms) {
		this.terms = terms;
	}

	public Set<Modificator> getModyficator() {
		return modyficators;
	}

	public void setModyficator(Set<Modificator> modyficators) {
		this.modyficators = modyficators;
	}
	
	public double getMin(){
		return min;
	}
	
	public double getMax(){
		return max;
	}
	
	public void addFuzzySet(FuzzySet set){
		terms.put(set.getId(), set);
	}
	
	public FuzzySet getFuzzySet(int id){
		return terms.get(id);
	}

}
