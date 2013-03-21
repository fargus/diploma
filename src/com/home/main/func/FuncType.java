package com.home.main.func;

public enum FuncType {

	TRIANGLE(1), TRAPEZE(2), Z(3), ZSPLAIN(4), S(5), SSPLAIN(6), LINEUP(7), LINEDOWN(
			8), PCOMPLEX(9), PBELL(10), PPROB(11);

	private int type;

	private FuncType(int type) {
		this.type = type;
	}
	
	public int getTypeCode(){
		return type;
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
