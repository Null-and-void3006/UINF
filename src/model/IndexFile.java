package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.swing.JTree;

import dataTree.DataRecordNode;
import dataTree.DataTableNode;
import dataTree.DataZoneNode;
import fileHandling.SerializationHandler;
import treeInfoModel.TableInfo;
import treeNodeModel.TableNode;



public class IndexFile extends DataFile implements FileOps{
	private ArrayList<IndexTable> indexTables;
	
	public IndexFile(TableNode table) {
		super(table);
		indexTables=new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	public void makeIndexTable(){
		this.sortMDI();
		for(int i=1000;i<table.getInfo().getRecords().size()-1;i+=1000){
			indexTables.add(new IndexTable(i, table.getInfo().getRecords().get(i).toString()));
		}
		indexTables.add(new IndexTable(table.getInfo().getRecords().size()-1, table.getInfo().getRecords().get(table.getInfo().getRecords().size()-1).toString()));
	}
	public void makeTree(){
		DataTableNode root=new DataTableNode(table.getInfo().getName());
		for(int i=0;i<indexTables.size()-1;i++){
			root.add(new DataZoneNode(indexTables.get(i).getValue()));
		}
		int cnt=0;
		int crit=1000;
		for(int i=0;i<table.getInfo().getRecords().size()-1;i++){
			if(i>crit){cnt++; crit+=1000;}
			root.getZones().get(cnt).add(new DataRecordNode(table.getInfo().getRecords().get(i).toString()));
		}
		SerializationHandler.writeObject(table.getInfo().getName()+".tree", root);
	}

	@Override
	public boolean fetchBlock(int start, int end) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRecord(Record record) {
		table.getInfo().getRecords().add(record);
		sortMDI();
		return true;
	}

	@Override
	public boolean updateRecord(Record recordOld, Record recordNew) {
		table.getInfo().getRecords().remove(recordOld);
		table.getInfo().getRecords().add(recordNew);
		this.sortMDI();
		return true;
	}

	@Override
	public Record findRecord(Record searchRec, int position) {
		int pom=-1;
		int l,d;
		l=0;
		d=table.getInfo().getRecords().size()-1;
		while(l<d){
			if(table.getInfo().getRecords().get((l+d)/2).compareTo(searchRec)==1) {l=(l+d)/2; continue;}
			if(table.getInfo().getRecords().get((l+d)/2).compareTo(searchRec)==0) return table.getInfo().getRecords().get((l+d)/2);
			d=(l+d)/2;
		}
		return null;
	}

	@Override
	public boolean deleteRecord(Record searchRec) {
		table.getInfo().getRecords().remove(searchRec);
		return false;
	}

	@Override
	public void sortMDI() {
		Collections.sort(table.getInfo().getRecords());
		
	}

	@Override
	public void sortMM() {
		// TODO Auto-generated method stub
		
	}


}
