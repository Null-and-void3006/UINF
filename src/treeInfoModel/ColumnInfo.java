package treeInfoModel;

import java.io.Serializable;

import model.Field;

public class ColumnInfo implements Serializable{
	private String name;
	private Field field;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	
}
