package com.home.main.func;

public class SSplainFunc extends AbstractFunc {

	public SSplainFunc(double a, double b) {
		this.a = a;
		this.b = b;
		this.ft=FuncType.SSPLAIN;
	}

	@Override
	public double getValue(double x) {
		if (x <= a) {
			return 0;
		} else if (x >= b) {
			return 1;
		} else if (x > a && x <= (a + b) / 2) {
			return 2 * Math.pow((x - a) / (b - a), 2);
		}
		return 1 - 2 * Math.pow((b - x) / (b - a), 2);
	}

}
