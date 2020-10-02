package dataTree;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import treeInfoModel.InfoModels;
import treeNodeModel.TreeNodeOverrides;

public class DataTableNode extends DefaultMutableTreeNode implements TreeNodeOverrides, Serializable{
	private ArrayList<DataZoneNode> zones;
	private String name;
	public DataTableNode(String name) {
		this.name=name;
		zones=new ArrayList<>();
	}

	@Override
	public void addChild(InfoModels m) {
		DataZoneNode d=(DataZoneNode)m;
		d.setParent(this);
		zones.add(d);
		
	}

	@Override
	public void addChild(DefaultMutableTreeNode m) {
		DataZoneNode d=(DataZoneNode)m;
		d.setParent(this);
		zones.add(d);
	}

	public ArrayList<DataZoneNode> getZones() {
		return zones;
	}

	public void setZones(ArrayList<DataZoneNode> zones) {
		this.zones = zones;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
