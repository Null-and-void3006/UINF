package model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import treeNodeModel.TableNode;


public class SerialFile extends DataFile implements FileOps, Serializable{
	
	public SerialFile(TableNode table) {
		super(table);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean fetchBlock(int start, int end) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRecord(Record record) {
		table.getInfo().getRecords().add(record);
		return false;
	}

	@Override
	public boolean updateRecord(Record recordOld, Record recordNew) {
		if(recordNew==null) return false;
		table.getInfo().getRecords().remove(recordOld);
		table.getInfo().getRecords().add(recordNew);
		return true;
	}

	@Override
	public Record findRecord(Record searchRec, int pos) {
		Record rez=new Record();
		for(Record r:table.getInfo().getRecords()){
			if(r.equals(searchRec)) rez=r;
		}
		return rez;
		
	}

	@Override
	public boolean deleteRecord(Record searchRec) {
		Record pom=new Record();
		for(Record r:table.getInfo().getRecords()){
			if(r.equals(searchRec)) pom=r;
		}
		table.getInfo().getRecords().remove(pom);
		return true;
	}

	@Override
	public void sortMDI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortMM() {
		// TODO Auto-generated method stub
		
	}
	
	
}
