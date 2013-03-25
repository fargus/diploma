package com.home.main.variable;

import com.home.main.db.entities.ModificatorDO;

public enum Modificator {
	
	CON(1, "very", 0.5), DIL(2, "more less", 2);
	
	private int id;
	private double factor;
	private String name;
	
	private Modificator(int id, String name, double factor){
		this.id = id;
		this.name=name;
		this.factor=factor;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public double getValue(double x){
		return Math.pow(x, factor);
	}
	
	public static Modificator getModById(int id){
		return values()[id];
	}
	
	public ModificatorDO getDO(){
		return new ModificatorDO(getId(), getName(), null, null);
	}

}
