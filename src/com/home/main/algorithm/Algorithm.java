package com.home.main.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.home.main.fuzzyset.ActivatedFuzzySet;
import com.home.main.fuzzyset.UnionOfFuzzySet;
import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.rule.Rule;
import com.home.main.rule.RuleBase;
import com.home.main.variable.Variable;

public class Algorithm {

	private static final Logger log = Logger.getLogger(Algorithm.class);

	private RuleBase rb;
	private Map<Integer, Double> inputVals;
	private Map<Integer, Double> outputVals;

	private AggregationType aggrType;
	private ImplicationType actType;
	private AccumulationType accumType;

	public void setAggrType(AggregationType aggrType) {
		this.aggrType = aggrType;
	}

	public void setActType(ImplicationType actType) {
		this.actType = actType;
	}

	public void setAccumType(AccumulationType accumType) {
		this.accumType = accumType;
	}

	public Map<Integer, Double> run(Map<Integer, Double> input) throws Exception {
		log.info("Algorithm start >>>");
		for (int i : input.keySet()) {
			log.debug("Input value: Variable:[" + i + "] Value:[" + input.get(i) + "]");
		}
		inputVals = input;
		outputVals = defuzzification(accumulation(activation(aggregation(fuzzyfication(inputVals)))));
		for (int i : outputVals.keySet()) {
			log.debug("Output value: Variable:[" + i + "] Value:[" + outputVals.get(i) + "]");
		}
		log.info("Algorithm stop <<<");

		return outputVals;
	}

	public void setInputVals(Map<Integer, Double> inputVals) {
		this.inputVals = inputVals;
	}

	public void setRuleBase(RuleBase rb) {
		this.rb = rb;
	}

	public Map<Integer, Double> getLastResult() {
		return outputVals;
	}

	private Map<Integer, Double> fuzzyfication(Map<Integer, Double> inputVals) throws Exception {
		log.trace("Fuzzification start!");
		Map<Integer, Double> result = new HashMap<Integer, Double>();
		for (Condition c : rb.getConditions()) {
			Double inputVal = inputVals.get(c.getVar().getId());
			if (inputVal == null) {
				throw new Exception("Supplied input value is null: Variable:[" + c.getVar().getId() + "]");
			}
			result.put(c.getId(), c.getValue(inputVal));
			log.trace("Result : Condition id:[" + c.getId() + "] Input value:[" + inputVal + "] Func value:[" + c.getValue(inputVal) + "]");
		}
		log.trace("Fuzzification complite!");
		return result;
	}

	private Map<Integer, Double> aggregation(Map<Integer, Double> inputVals) {
		log.trace("Aggregation start!");
		Map<Integer, Double> result = new HashMap<Integer, Double>();
		for (Rule r : rb.getRules()) {
			Double resVal = .0;
			Iterator<Condition> itr = r.getConditions().iterator();
			if (itr.hasNext()) {
				Condition c = itr.next();
				resVal = inputVals.get(c.getId());
				log.trace("First Condition:[" + c.getId() + "] Condition value:[" + inputVals.get(c.getId()) + "]");
			}
			while (itr.hasNext()) {
				Condition c = itr.next();
				resVal = aggrType.getValue(resVal, inputVals.get(c.getId()), c.getOperator()); // can
																								// be

				log.trace("Condition:[" + c.getId() + "] Condition value:[" + inputVals.get(c.getId()) + "] Condition operator:[" + c.getOperator()
						+ "] Result value:[" + resVal + "]");
			}

			log.trace("Result : Rule #" + r.getId() + " Aggregation value:[" + resVal + "]");

			result.put(r.getId(), resVal);
		}
		log.trace("Aggregation complite!");

		return result;
	}

	private Map<Integer, ActivatedFuzzySet> activation(Map<Integer, Double> inputVal) {
		log.trace("Activation start!");
		Map<Integer, ActivatedFuzzySet> result = new HashMap<Integer, ActivatedFuzzySet>();
		for (Rule r : rb.getRules()) {
			for (Conclusion c : r.getConclusions()) {
				double temp = actType.getValue(c.getWeight(), inputVal.get(r.getId()));
				result.put(c.getId(), new ActivatedFuzzySet(temp, c.getTerm())); // can
																					// be
																					// changed
				log.trace("Rule #" + r.getId() + " Conclusion:[" + c.getId() + "] Conclusion weight:[" + c.getWeight() + "] Input value:["
						+ inputVal.get(r.getId()) + "] Truth degree:[" + temp + "]");
			}
		}

		log.trace("Activation complite!");
		return result;
	}

	private Map<Integer, UnionOfFuzzySet> accumulation(Map<Integer, ActivatedFuzzySet> inputVal) {
		log.trace("Accumulation start!");
		Map<Integer, UnionOfFuzzySet> unions = new HashMap<Integer, UnionOfFuzzySet>();
		for (Rule r : rb.getRules()) {
			for (Conclusion c : r.getConclusions()) {
				UnionOfFuzzySet u = unions.get(c.getVar().getId());
				if (u == null) {
					u = new UnionOfFuzzySet();
					u.setAccumulationType(accumType);
					unions.put(c.getVar().getId(), u);
				}
				u.addFuzzySet(inputVal.get(c.getId()));
				log.trace("Rule #" + r.getId() + " Variable:[" + c.getVar().getId() + "] Activated term added to union:[" + inputVal.get(c.getId()).getId()
						+ "]");
			}
		}

		log.trace("Accumulation complite!");
		return unions;
	}

	private Map<Integer, Double> defuzzification(Map<Integer, UnionOfFuzzySet> inputVal) {
		log.trace("Defuzzification start!");

		Map<Integer, Double> outputValues = new HashMap<Integer, Double>();
		for (Variable v : rb.getOutputVars()) {
			double i1 = integral2(v.getMin(), v.getMax(), inputVal.get(v.getId()), true);
			double i2 = integral2(v.getMin(), v.getMax(), inputVal.get(v.getId()), false);
			outputValues.put(v.getId(), i1 / i2);
			log.trace("Variable:[" + v.getId() + "] Value:[" + outputValues.get(v.getId()) + "]");
		}

		log.trace("Defuzzyfucation complite!");
		return outputValues;
	}

	@SuppressWarnings("unused")
	private double integral(double min, double max, UnionOfFuzzySet u, boolean isUp) {
		log.trace("Integral start!");

		for (double i = min; i <= max; i += (max - min) / 20) {
			log.trace("Union value:[" + i + "]-[" + u.getValue(i) + "]");
		}

		double result = .0;
		double n = min;
		double step = (max - min) / 1000;
		while (n < max - step) {
			double f1;
			double f2;
			if (isUp) {
				f1 = u.getValue(n);
				f2 = u.getValue(n + step);
			} else {
				f1 = n * u.getValue(n);
				f2 = (n + step) * u.getValue(n + step);
			}
			result += ((f1 + f2) / 2) * step;
			n += step;
		}

		log.trace("Integral complite!");
		return result;
	}

	private double integral2(double min, double max, UnionOfFuzzySet u, boolean isUp) {
		log.trace("Integral start!");

		for (double i = min; i <= max; i += (max - min) / 20) {
			log.trace("Union value:[" + i + "]-[" + u.getValue(i) + "]");
		}

		double result = .0;
		for (double i = min; i <= max; i += 1) {
			if (isUp) {
				result += i * u.getValue(i);
			} else {
				result += u.getValue(i);
			}
		}

		log.trace("Integral complite!");
		return result;
	}
}
