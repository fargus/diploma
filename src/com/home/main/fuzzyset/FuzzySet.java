package com.home.main.fuzzyset;

import com.home.main.db.entities.FuzzySetDO;
import com.home.main.func.Func;

public interface FuzzySet {
	
	Integer getId();
	void setId(Integer id);
	String getName();
	double getValue(double d);
	FuzzySetDO getDO();
	Func getFunc();
}
