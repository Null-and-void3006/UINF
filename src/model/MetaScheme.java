package model;

import java.util.ArrayList;

public class MetaScheme {
	private ArrayList<TableInfoStorage> tables;
	private String content;
	private String name;
	
	public MetaScheme(ArrayList<TableInfoStorage> tables) {
		// TODO Auto-generated constructor stub
		this.tables = tables;
		content = "";
		for(TableInfoStorage tab : tables){
			content+=tab.toString();
		}
		
		
	}

	public ArrayList<TableInfoStorage> getTables() {
		return tables;
	}

	public void setTables(ArrayList<TableInfoStorage> tables) {
		this.tables = tables;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
