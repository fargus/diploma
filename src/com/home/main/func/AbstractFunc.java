package com.home.main.func;

import com.home.main.db.entities.FuncDO;

public abstract class AbstractFunc implements Func{
	
	private Integer id;
	
	protected Double a;
	protected Double b;
	protected Double c;
	protected Double d;
	
	protected FuncType ft;
	
	public FuncType getFuncType(){
		return ft;
	}
	
	public FuncDO getDO(){
		return new FuncDO(id, a, b, c, d, ft.getTypeCode(), null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("FUNC:");
		if(id != null){
			sb.append(id+" ");
		}else{
			sb.append("# ");
		}
		sb.append("[a:["+a+"] b:["+b+"] c:["+c+"] d:["+ d+"]] Type="+ft.toString());
		return sb.toString();
	}
	
	public Double getA(){
		return a;
	}
	public Double getB(){
		return b;
	}
	public Double getC(){
		return c;
	}
	public Double getD(){
		return d;
	}
	
	public FuncType getType(){
		return ft;
	}
}
