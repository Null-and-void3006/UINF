package treeNodeModel;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import treeInfoModel.IRInfo;
import treeInfoModel.InfoModels;
import treeInfoModel.TableInfo;

public class IRNode extends DefaultMutableTreeNode implements TreeNodeOverrides {
	private ArrayList<TableNode> tables=new ArrayList<>();
	private IRInfo info;
	private MutableTreeNode parent;
	
	public IRNode(IRInfo k) {
		info=k;
	}
	public IRNode() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addChild(InfoModels m) {
		// TODO Auto-generated method stub
		if(m instanceof TableInfo){
			TableNode t=new TableNode((TableInfo)m);
			t.setParent(this);
			tables.add(t);
			
		}
	}

	public IRInfo getInfo() {
		return info;
	}
	@Override
	public void addChild(DefaultMutableTreeNode m) {
		// TODO Auto-generated method stub
		
		if(m instanceof TableNode){
	
			m.setParent(this);
			tables.add((TableNode)m);
		}
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
	
	public boolean getAllowsChildren(){return true;}
	public void remove(MutableTreeNode aChild){tables.remove(aChild);}

	public void remove(int childIndex) {tables.remove(childIndex);}

	public void removeAllChildren(){tables.clear();}

	public TreeNode getChildAt(int index){return tables.get(index);}

	public boolean isLeaf(){return false;}
	
	public int getChildCount(){return tables.size();}
	@Override
	public void add(MutableTreeNode newChild) {
		if(newChild instanceof DefaultMutableTreeNode)
		addChild((DefaultMutableTreeNode)newChild);
	}

	
	
}
