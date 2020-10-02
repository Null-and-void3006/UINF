package model;

import java.io.Serializable;

import treeNodeModel.TableNode;

public class TableDescription implements Serializable{
	private String content;
	private String path;
	private DataFile file;
	private String tree;
	private TableNode table;
	private int numFields;
	
	public TableDescription(String content, String path, DataFile file, String tree, TableNode table, int numFields) {
		super();
		this.content = content;
		this.path = path;
		this.file = file;
		this.tree = tree;
		this.table = table;
		this.numFields = numFields;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DataFile getFile() {
		return file;
	}

	public void setFile(DataFile file) {
		this.file = file;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public TableNode getTable() {
		return table;
	}

	public void setTable(TableNode table) {
		this.table = table;
	}

	public int getNumFields() {
		return numFields;
	}

	public void setNumFields(int numFields) {
		this.numFields = numFields;
	}
	
	
}
