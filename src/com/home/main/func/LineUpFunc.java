package com.home.main.func;

public class LineUpFunc extends AbstractFunc {

	public LineUpFunc(double a, double b){
		this.a=a;
		this.b=b;
		this.ft=FuncType.LINEUP;
	}

	@Override
	public double getValue(double x) {
		if (x<=a){
			return 0;
		}else if(x>=b){
			return 1;
		}else{
			return (x-a)/(b-a);
		}
	}

}
