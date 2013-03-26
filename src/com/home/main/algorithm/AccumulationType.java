package com.home.main.algorithm;

public enum AccumulationType {
	 ALG_SUM, MAX_DIS, BOUND_SUM, DRAG_SUM, LAMBDA_SUM;

	public Double getValue(Double a, Double b) {
		switch (this) {
		case MAX_DIS: {
			return maxDis(a, b);
		}
		case ALG_SUM: {
			return algSum(a, b);
		}
		case BOUND_SUM: {
			return boundSum(a, b);
		}
		case DRAG_SUM: {
			return dragSum(a, b);
		}
		case LAMBDA_SUM: {
			return lambdaSum(a, b);
		}
		default: {
			return maxDis(a, b);
		}
		}
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

	private double lambdaSum(double a, double b) {
		double l = 0.5;
		return l * a + (1 - l) * b;
	}
}
