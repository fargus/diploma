package com.home.main.db.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.home.main.func.Func;
import com.home.main.func.FuncType;
import com.home.main.func.FuncUtil;


@Entity
@Table(name="func")
public class FuncDO implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private Double a;
	@Column(nullable = false)
	private Double b;
	@Column
	private Double c;
	@Column
	private Double d;
	@Column(nullable = false)
	private int type;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "func")
	private Set<FuzzySetDO> terms;
	
	public FuncDO(){
	}
	
	public FuncDO(Integer id, Double a, Double b, Double c, Double d, int type,
			Set<FuzzySetDO> terms) {
		this.id = id;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.type = type;
		this.terms = terms;
	}

	public int getId() {
		return id;
	}

	public Double getA() {
		return a;
	}

	public Double getB() {
		return b;
	}

	public Double getC() {
		return c;
	}

	public Double getD() {
		return d;
	}

	public int getType() {
		return type;
	}

	public Set<FuzzySetDO> getTerms() {
		return terms;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setA(Double a) {
		this.a = a;
	}

	public void setB(Double b) {
		this.b = b;
	}

	public void setC(Double c) {
		this.c = c;
	}

	public void setD(Double d) {
		this.d = d;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setTerms(Set<FuzzySetDO> terms) {
		this.terms = terms;
	}
	
	public Func getDTO(){
		Func f = FuncUtil.createFunc(a, b, c, d, FuncType.getTypeByCode(type));
		f.setId(id);
		return f;
	}

}
