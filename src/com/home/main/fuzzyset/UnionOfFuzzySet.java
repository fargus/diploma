package com.home.main.fuzzyset;

import java.util.ArrayList;
import java.util.List;

import com.home.main.algorithm.AccumulationType;
import com.home.main.db.entities.FuzzySetDO;
import com.home.main.func.Func;

public class UnionOfFuzzySet implements FuzzySet {
	
	private List<FuzzySet> sets;
	private AccumulationType accType;
	
	public UnionOfFuzzySet(){
		sets = new ArrayList<FuzzySet>();
	}
	
	public UnionOfFuzzySet(AccumulationType accType){
		sets = new ArrayList<FuzzySet>();
		this.accType = accType;
	}

	@Override
	public String getName() {
		return "UNION: ";
	}

	@Override
	public double getValue(double d) {
		double result = .0;
		for(FuzzySet fs : sets){
			result = accType.getValue(result, fs.getValue(d));
		}
		
		return result;
	}

	public void addFuzzySet(FuzzySet set){
		sets.add(set);
	}

	@Override
	public Integer getId() {
		return null;
	}

	@Override
	public FuzzySetDO getDO() {
		return null;
	}

	@Override
	public void setId(Integer id) {
	}
	
	public void setAccumulationType(AccumulationType accumType){
		this.accType = accumType;
	}

	@Override
	public Func getFunc() {
		return null;
	}
	
	public void clear(){
		sets.clear();
	}
}
