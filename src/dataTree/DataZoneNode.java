package dataTree;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import treeInfoModel.InfoModels;
import treeNodeModel.TreeNodeOverrides;

public class DataZoneNode extends DefaultMutableTreeNode implements TreeNodeOverrides, Serializable{
	private ArrayList<DataRecordNode> records;
	private String name;
	public DataZoneNode(String name) {
		this.name=name;
		records=new ArrayList<>();
	}

	@Override
	public void addChild(InfoModels m) {
		DataRecordNode d=(DataRecordNode)m;
		d.setParent(this);
		records.add(d);
		
	}

	@Override
	public void addChild(DefaultMutableTreeNode m) {
		DataRecordNode d=(DataRecordNode)m;
		d.setParent(this);
		records.add(d);
	}

	public ArrayList<DataRecordNode> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<DataRecordNode> records) {
		this.records = records;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
