package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Record implements Comparable<Record>, Serializable{
	private String key=" ";
	private ArrayList<String> values;
	public Record( ArrayList<String> values) {
		// TODO Auto-generated constructor stub
		
		this.values=values;
	}
	
	public Record() {
		// TODO Auto-generated constructor stub
		values= new ArrayList<>();
	}
	
	public ArrayList<String> getValues() {
		return values;
	}
	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
	
	public void addValue(String s){values.add(s);}
	@Override
	public boolean equals(Object r){
		if(((Record)r).getValues().size()!=this.values.size()) return false;
		for(int i=0;i<values.size()-1;i++){
			if(((Record)r).getValues().get(i)!=values.get(i)) return false;
		}
		return true;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public int compareTo(Record o) {
		if(key.compareTo(o.getKey())>0) return 1;
		if(key.compareTo(o.getKey())<0) return -1;
		return key.compareTo(o.getKey());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(String s: values){sb.append(s);}
		return sb.toString();
	}
	
	
}
