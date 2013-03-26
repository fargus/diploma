package com.home.main.algorithm;

public enum ImplicationType {

	MOMDANY, KLINEDAISE, ZADDE, LARSEN, LUKASHEVICH, GEDEL, GEINS, GOGUEN, KLINE_DEISE_LUK, FUZZY_PROBABILITY, LIMITED_FUZZY_PROB, VADI, YAGR;

	public Double getValue(Double a, Double b) {
		switch (this) {
		case KLINEDAISE: {
			return klineDaise(a, b);
		}
		case ZADDE: {
			return zadde(a, b);
		}
		case FUZZY_PROBABILITY: {
			return fuzzyProb(a, b);
		}
		case GEDEL: {
			return gedel(a, b);
		}
		case GEINS: {
			return gains(a, b);
		}
		case GOGUEN: {
			return goguen(a, b);
		}
		case KLINE_DEISE_LUK: {
			return klineDaiseLuk(a, b);
		}
		case LARSEN: {
			return larsen(a, b);
		}
		case LIMITED_FUZZY_PROB: {
			return limitedFuzzyProb(a, b);
		}
		case LUKASHEVICH: {
			return lukashevich(a, b);
		}
		case MOMDANY: {
			return momdany(a, b);
		}
		case VADI: {
			return vadi(a, b);
		}
		case YAGR: {
			return yagr(a, b);
		}
		default: {
			return 1.;
		}
		}
	}

	private double klineDaise(double a, double b) {
		if (a > b) {
			return Math.max(1 - a, b);
		}
		return Math.max(1 - b, a);
	}

	private double zadde(double a, double b) {
		return Math.max(Math.min(a, b), 1 - a);
	}

	private double momdany(double a, double b) {
		return Math.min(a, b);
	}

	private double larsen(double a, double b) {
		return a * b;
	}

	private double lukashevich(double a, double b) {
		return Math.min(1, 1 - a + b);
	}

	private double gedel(double a, double b) {
		if (a < b) {
			return 1;
		}
		return b;
	}

	private double gains(double a, double b) {
		if (a < b) {
			return 1;
		}
		return a * b;
	}

	private double goguen(double a, double b) {
		return Math.min(1, a / b);
	}

	private double klineDaiseLuk(double a, double b) {
		return 1 - a + a * b;
	}

	private double fuzzyProb(double a, double b) {
		return Math.min(1, 1 - a + a * b);
	}

	private double limitedFuzzyProb(double a, double b) {
		return Math.min(1, a + b);
	}

	private double vadi(double a, double b) {
		return Math.max(a * b, 1 - a);
	}

	private double yagr(double a, double b) {
		return Math.pow(a, b);
	}

}
