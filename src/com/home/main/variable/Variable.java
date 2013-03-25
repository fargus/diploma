package com.home.main.variable;

import java.util.HashSet;
import java.util.Set;

import com.home.main.db.entities.FuzzySetDO;
import com.home.main.db.entities.ModificatorDO;
import com.home.main.db.entities.VariableDO;
import com.home.main.fuzzyset.FuzzySet;

public class Variable {

	private Integer id;

	private String name;
	private Set<FuzzySet> terms;
	private Set<Modificator> modyficators;
	private double min;
	private double max;

	public Variable(Integer id, String name, double min, double max) {
		this(id, name, null, min, max, null);
		setModyficator(new HashSet<Modificator>());
		setTerms(new HashSet<FuzzySet>());
	}
	public Variable(Integer id, String name, Set<FuzzySet> terms, double min, double max,
			Set<Modificator> modyficators) {
		this.id = id;
		this.name = name;
		this.terms = terms;
		this.modyficators = modyficators;
		this.min = min;
		this.max = max;
	}
	
	public Variable(String name, Set<FuzzySet> terms, double min, double max,
			Set<Modificator> modyficators) {
		this(null, name, terms, min, max, modyficators);
	}
	
	public Variable(String name, double min, double max) {
		this(null, name, min, max);
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

	public Set<FuzzySet> getTerms() {
		return terms;
	}

	public void setTerms(Set<FuzzySet> terms) {
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
		terms.add(set);
	}
	
	public FuzzySet getFuzzySet(Integer id){
		for(FuzzySet fs : terms){
			if (fs.getId() == id){
				return fs;
			}
		}
		return null;
	}

	public VariableDO getDO(){
		Set<FuzzySetDO> terms = new HashSet<FuzzySetDO>();
		for(FuzzySet fs : this.terms){
			terms.add(fs.getDO());
		}
		Set<ModificatorDO> mods = new HashSet<ModificatorDO>();
		for(Modificator m : this.modyficators){
			mods.add(m.getDO());
		}
		return new VariableDO(name, min, max, terms, null, mods);
	}
	
}
