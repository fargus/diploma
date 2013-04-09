package com.home.main.variable;

import java.util.HashSet;
import java.util.Set;

import com.home.main.db.entities.FuzzySetDO;
import com.home.main.db.entities.ModificatorDO;
import com.home.main.db.entities.VariableDO;
import com.home.main.fuzzyset.FuzzySet;

public class Variable {

	private Integer id;

	private String name;
	private Set<FuzzySet> terms;
	private Set<Modificator> modyficators;
	private double min;
	private double max;

	public Variable(Integer id, String name, double min, double max) {
		this(id, name, null, min, max, null);
		setModyficator(new HashSet<Modificator>());
		setTerms(new HashSet<FuzzySet>());
	}
	
	
	public Variable(Integer id, String name, Set<FuzzySet> terms, double min, double max,
			Set<Modificator> modyficators) {
		this.id = id;
		this.name = name;
		this.terms = terms;
		this.modyficators = modyficators;
		this.min = min;
		this.max = max;
	}
	
	public Variable(String name, Set<FuzzySet> terms, double min, double max,
			Set<Modificator> modyficators) {
		this(null, name, terms, min, max, modyficators);
	}
	
	public Variable(String name, Set<FuzzySet> terms, double min, double max) {
		this(null, name, terms, min, max, null);
		setModyficator(new HashSet<Modificator>());
	}
	
	public Variable(String name, double min, double max) {
		this(null, name, min, max);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<FuzzySet> getTerms() {
		return terms;
	}

	public void setTerms(Set<FuzzySet> terms) {
		this.terms = terms;
	}

	public Set<Modificator> getModyficator() {
		return modyficators;
	}

	public void setModyficator(Set<Modificator> modyficators) {
		this.modyficators = modyficators;
	}
	
	public double getMin(){
		return min;
	}
	
	public double getMax(){
		return max;
	}
	
	public void setMin(double min){
		this.min=min;
	}
	
	public void setMax(double max){
		this.max=max;
	}
	
	public void addFuzzySet(FuzzySet set){
		terms.add(set);
	}
	
	public FuzzySet getFuzzySet(Integer id){
		for(FuzzySet fs : terms){
			if (fs.getId() == id){
				return fs;
			}
		}
		return null;
	}

	public VariableDO getDO(){
		Set<FuzzySetDO> terms = new HashSet<FuzzySetDO>();
		for(FuzzySet fs : this.terms){
			terms.add(fs.getDO());
		}
		Set<ModificatorDO> mods = new HashSet<ModificatorDO>();
		if (this.modyficators != null){
			for(Modificator m : this.modyficators){
				mods.add(m.getDO());
			}
		}

		return new VariableDO(id, name, min, max, terms, null, mods);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(max);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(min);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Variable other = (Variable) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(max) != Double.doubleToLongBits(other.max))
			return false;
		if (Double.doubleToLongBits(min) != Double.doubleToLongBits(other.min))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Variable: ");
		if(id != null){
			sb.append("ID=["+id+"] ");
		}
		sb.append("Name=["+name+"] ");
		sb.append("Min=["+min+"] ");
		sb.append("Max=["+max+"] ");
		sb.append("Terms=[ ");
		if (terms != null){
			for(FuzzySet f : terms){
				sb.append("["+f.getId()+":"+f.getName()+"] ");
			}
		}
		sb.append("] ");
		
		sb.append("Mods=[ ");
		if (modyficators != null){
			for(Modificator m : modyficators){
				sb.append("["+m.toString()+"]");
			}
		}
		sb.append("] ");
		return sb.toString();
	}
}
