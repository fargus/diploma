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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((ft == null) ? 0 : ft.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractFunc other = (AbstractFunc) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		if (ft != other.ft)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
