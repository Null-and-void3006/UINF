package model;

public class IndexTable {
	private int endIndex;
	private String value;
	public IndexTable(int endIndex, String value) {
		super();
		this.endIndex = endIndex;
		this.value = value;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
