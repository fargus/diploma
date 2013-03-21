package com.home.main.variable;

public enum Modificator {
	
	CON("very", 0.5), DIL("more less", 2);
	
	private double factor;
	private String name;
	
	private Modificator(String name, double factor){
		this.name=name;
		this.factor=factor;
	}
	
	public String getName(){
		return name;
	}
	
	public double getValue(double x){
		return Math.pow(x, factor);
	}

}
