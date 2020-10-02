package treeInfoModel;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import model.Record;

public class TableInfo implements Serializable{
	private String name="*PLACEHOLDER*";
	private ArrayList<Record> records=new ArrayList<>();

	
	public String getName() {
		return new File(name).getName().replaceFirst("[.][^.]+$", "");
		//return name;
	}

	public String getPath(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Record> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<Record> records) {
		this.records = records;
	}
	public Record addRecord(){
		Record rc=new Record();
		records.add(rc);
		return rc;
	}

	
	
	
}
