package com.home.main.variable;

public enum Operator {

	AND(0), OR(1);
	
	private int id;
	
	private Operator(int id){
		this.id = id;
	}
	
	public int getCode(){
		return id;
	}
	
	public static Operator getOp(int code){
		for(Operator op : values()){
			if (op.id == code){
				return op;
			}
		}
		
		return null;
	}
}
