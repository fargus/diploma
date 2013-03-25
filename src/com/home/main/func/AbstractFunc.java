package com.home.main.func;

import com.home.main.db.entities.FuncDO;

public abstract class AbstractFunc implements Func{
	
	private Integer id;
	
	protected double a;
	protected double b;
	protected double c;
	protected double d;
	
	protected FuncType ft;
	
	public FuncType getFuncType(){
		return ft;
	}
	
	public FuncDO getDO(){
		return new FuncDO(id, a, b, c, d, ft.getTypeCode(), null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
