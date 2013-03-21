package com.home.main.func;

public class TrapezeFunc extends TriangleFunc {

	public TrapezeFunc(double a, double b, double c, double d) {
		super(a, b, c);
		this.d = d;
		this.ft=FuncType.TRAPEZE;
	}

	@Override
	public double getValue(double v) {
		if (v >= super.b && v <= d) {
			return 1;
		} else if (v >= d && v <= c) {
			return (super.c - v) / (super.c - d);
		}
		return super.getValue(v);
	}

}
