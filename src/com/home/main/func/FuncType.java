package com.home.main.func;

public enum FuncType {

	TRIANGLE(1, 3), TRAPEZE(2, 4), Z(3, 2), ZSPLAIN(4, 2), S(5, 2), SSPLAIN(6, 2), LINEUP(7, 2), LINEDOWN(
			8, 2), PCOMPLEX(9, 4), PBELL(10, 3), PPROB(11, 2);

	private int type;
	private int num_args;

	private FuncType(int type, int num_args) {
		this.type = type;
		this.num_args = num_args;
	}
	
	public int getTypeCode(){
		return type;
	}
	
	public int getNumArgs(){
		return num_args;
	}

	public static FuncType getTypeByCode(int code){
		for(FuncType ft : values()){
			if (ft.type == code){
				return ft;
			}
		}
		
		throw new RuntimeException("FuncType "+code+" does't support");
	}
}
