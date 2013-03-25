package com.home.main.algorithm;

public enum AggregationType {
	MIN_CON, ALG_MUL, BOUND_MUL, DRAG_MUL;

	public Double getVaDouble(Double a, Double b) {
		switch (this) {
		case MIN_CON: {
			return minCon(a, b);
		}
		case ALG_MUL: {
			return algMul(a, b);
		}
		case BOUND_MUL: {
			return boundMul(a, b);
		}
		case DRAG_MUL: {
			return dragMul(a, b);
		}
		default: {
			return minCon(a, b);
		}
		}
	}

	private double minCon(double a, double b) {
		return Math.min(a, b);
	}

	private double algMul(double a, double b) {
		return a * b;
	}

	private double boundMul(double a, double b) {
		return Math.max(a + b - 1, 0);
	}

	private double dragMul(double a, double b) {
		if (a == 1) {
			return b;
		}
		if (b == 1) {
			return a;
		}
		return 0;
	}
}
