package com.home.main.func;

public class TriangleFunc extends AbstractFunc {

	public TriangleFunc(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.ft=FuncType.TRIANGLE;
	}

	@Override
	public double getValue(double v) {
		if (v<=a || v>=c){
			return 0;
		}else if (v>=a && v<=b){
			return (v-a)/(b-a);
		}else{
			return (c-v)/(c-b);
		}
	}

}
