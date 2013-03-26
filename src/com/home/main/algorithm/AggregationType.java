package com.home.main.algorithm;

import com.home.main.variable.Operator;

public enum AggregationType {
	 ALG_MUL, MIN_CON, BOUND_MUL, DRAG_MUL;

	public Double getValue(Double a, Double b, Operator op) {
		switch (this) {
		case MIN_CON: {
			return (op == Operator.AND)?minCon(a, b): maxDis(a, b);
		}
		case ALG_MUL: {
			return (op == Operator.AND)?algMul(a, b):algSum(a, b);
		}
		case BOUND_MUL: {
			return (op == Operator.AND)?boundMul(a, b):boundSum(a, b);
		}
		case DRAG_MUL: {
			return (op == Operator.AND)?dragMul(a, b):dragSum(a, b);
		}
		default: {
			return (op == Operator.AND)?algMul(a, b):algSum(a, b);
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
	
	private double maxDis(double a, double b) {
		return Math.max(a, b);
	}

	private double algSum(double a, double b) {
		return a + b - a * b;
	}

	private double boundSum(double a, double b) {
		return Math.min(a + b, 1);
	}

	private double dragSum(double a, double b) {
		if (a == 0 && b != 0) {
			return b;
		}
		if (b == 0 && a != 0) {
			return a;
		}

		return 1;
	}
}
