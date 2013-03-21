package com.home.main.func;

public class LineDownFunc extends AbstractFunc {

	public LineDownFunc(double a, double b){
		this.a=a;
		this.b=b;
		this.ft=FuncType.LINEDOWN;
	}
	
	@Override
	public double getValue(double x) {
		if (x<=1){
			return 1;
		}else if(x>=b){
			return 0;
		}else{
			return (b-x)/(b-a);
		}
	}

}
