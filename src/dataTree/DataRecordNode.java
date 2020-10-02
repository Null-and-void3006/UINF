package dataTree;

import java.io.Serializable;

import javax.swing.tree.DefaultMutableTreeNode;

import treeInfoModel.InfoModels;
import treeNodeModel.TreeNodeOverrides;

public class DataRecordNode extends DefaultMutableTreeNode implements TreeNodeOverrides, Serializable{
	private String name;
	public DataRecordNode(String name) {
		this.name=name;
	}

	@Override
	public void addChild(InfoModels m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addChild(DefaultMutableTreeNode m) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
