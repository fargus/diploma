package com.home.main.func;

public class PBellShapedFunc extends AbstractFunc {

	
	public PBellShapedFunc(double a, double b, double c){
		this.a=a;
		this.b=b;
		this.c=c;
		this.ft=FuncType.PBELL;
	}
	
	@Override
	public double getValue(double x) {
		return 1/1+Math.pow(Math.abs((x-c)/a), 2*b);
	}

}
