package com.home.main.func;

public class PComplexFunc extends AbstractFunc {

	private Func f1;
	private Func f2;
	
	public PComplexFunc(Func f1, Func f2){
		this.f1 = f1;
		this.f2 = f2;
		this.ft = FuncType.PCOMPLEX;
	}
	
	@Override
	public double getValue(double x) {
		return f1.getValue(x)*f2.getValue(x);
	}

}
