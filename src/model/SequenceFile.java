package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import app.AppCore;
import treeNodeModel.ColumnNode;
import treeNodeModel.TableNode;
import view.MainFrame;


public class SequenceFile extends DataFile implements FileOps,Serializable {

	public SequenceFile(TableNode table) {
		super(table);
	
	}



	@Override
	public boolean fetchBlock(int start, int end) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean addRecord(Record record) {
		table.getInfo().getRecords().add(record);
		this.sortMDI();
		System.out.println(table.getInfo().getRecords().indexOf(record));
		return false;
	}


	@Override
	public boolean updateRecord(Record recordOld, Record recordNew) {
		if(recordNew==null) return false;
		table.getInfo().getRecords().remove(recordOld);
		table.getInfo().getRecords().add(recordNew);
		this.sortMDI();
		return true;
	}


	@Override
	public Record findRecord(Record searchRec, int position) {
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
		this.sortMDI();
		return true;
	}


	@Override
	public void sortMDI() {
		//System.out.println(table.getInfo().getRecords().get(50).getKey());
		Collections.sort(table.getInfo().getRecords());
	}


	@Override
	public void sortMM() {
		// TODO Auto-generated method stub
		
	}

}
