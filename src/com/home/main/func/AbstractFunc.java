package com.home.main.func;

public abstract class AbstractFunc implements Func{
	
	protected double a;
	protected double b;
	protected double c;
	protected double d;
	
	protected FuncType ft;
	
	public FuncType getFuncType(){
		return ft;
	}
}
