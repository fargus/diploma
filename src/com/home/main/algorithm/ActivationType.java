package com.home.main.algorithm;

public enum ActivationType {
	MIN, PROD, AVERAGE;

	public Double getValue(Double a, Double b) {
		switch (this) {
		case MIN: {
			return minActivation(a, b);
		}
		case PROD: {
			return prodActivation(a, b);
		}
		case AVERAGE: {
			averageActivation(a, b);
		}
		default: {
			return 1.;
		}
		}
	}

	private double minActivation(double a, double b) {
		return Math.min(a, b);
	}

	private double prodActivation(double a, double b) {
		return a * b;
	}

	private double averageActivation(double a, double b) {
		return (a + b) / 2;
	}
}
