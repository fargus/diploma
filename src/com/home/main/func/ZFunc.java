package com.home.main.func;

public class ZFunc extends AbstractFunc {

	public ZFunc(double a, double b) {
		this.a = a;
		this.b = b;
		this.ft = FuncType.Z;
	}

	@Override
	public double getValue(double v) {
		if (v < a) {
			return 1;
		} else if (v > b) {
			return 0;
		}
		return 0.5 + 0.5 * Math.cos(((v - a) / (b - a)) * Math.PI);
	}

}
