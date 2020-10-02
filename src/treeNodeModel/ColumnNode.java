package treeNodeModel;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import treeInfoModel.ColumnInfo;
import treeInfoModel.InfoModels;

public class ColumnNode extends DefaultMutableTreeNode implements TreeNodeOverrides {
	private ColumnInfo info;
	private MutableTreeNode parent;
	
	public ColumnNode(ColumnInfo k) {
		info=k;
	}
	public ColumnNode() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addChild(InfoModels m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addChild(DefaultMutableTreeNode m) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return parent;
	}
	@Override
	public void setParent(MutableTreeNode newParent) {
		// TODO Auto-generated method stub
		parent=newParent;
	}
	@Override
	public String toString(){
		return info.getName();
	}
	public ColumnInfo getInfo() {
		return info;
	}
	public void setInfo(ColumnInfo ci) {
		info=ci;
	}

	public boolean getAllowsChildren(){return false;}
	

	public boolean isLeaf(){return true;}
	
}
