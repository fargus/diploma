package com.home.main.func;

import com.home.main.db.entities.FuncDO;

public interface Func {
	
	double getValue(double x);
	
	FuncDO getDO();
	
	public Integer getId();
	public void setId(Integer id);

}
