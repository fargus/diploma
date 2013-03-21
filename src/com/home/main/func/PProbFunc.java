package com.home.main.func;

public class PProbFunc extends AbstractFunc{

	public PProbFunc(double a, double b){
		this.a=a;
		this.b=b;
		this.ft=FuncType.PPROB;
	}
	
	@Override
	public double getValue(double x) {
		return Math.pow(Math.E, (-Math.pow(x-b, 2))/2*a*a);
	}

}
