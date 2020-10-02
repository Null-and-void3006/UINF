package model;

import treeNodeModel.TableNode;

public abstract class DataFile {
	protected TableNode table;
	
	public DataFile(TableNode table) {
		// TODO Auto-generated constructor stub
		this.table=table;
	}

	public TableNode getTable() {
		return table;
	}

	public void setTable(TableNode table) {
		this.table = table;
	}
	
}
