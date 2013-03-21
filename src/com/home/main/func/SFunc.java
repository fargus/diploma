package com.home.main.func;

public class SFunc extends AbstractFunc {

	public SFunc(double a, double b) {
		this.a = a;
		this.b = b;
		this.ft=FuncType.S;
	}

	@Override
	public double getValue(double x) {
		if (x < a) {
			return 0;
		} else if (x > b) {
			return 1;
		}
		return 0.5 + 0.5 * Math.cos(((x - b) / (b - a)) * Math.PI);
	}

}
