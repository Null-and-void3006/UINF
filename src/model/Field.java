package model;

import java.io.Serializable;

public class Field implements Serializable{
	private String name;
	private String type;
	private int length;
	private boolean isPK;
	private boolean isSorted;
	private boolean ascending;
	public Field(String name, String type, int length, boolean isPK, boolean isSorted, boolean ascending) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
		this.isPK = isPK;
		this.isSorted=isSorted;
		this.ascending=ascending;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public boolean isPK() {
		return isPK;
	}
	public void setPK(boolean isPK) {
		this.isPK = isPK;
	}
	public boolean isSorted() {
		return isSorted;
	}
	public void setSorted(boolean isSorted) {
		this.isSorted = isSorted;
	}
	public boolean isAscending() {
		return ascending;
	}
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
	
	
}
