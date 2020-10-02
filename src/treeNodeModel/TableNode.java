package treeNodeModel;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import app.AppCore;
import controller.Observed;
import controller.Observer;
import model.ForeignTableBundle;
import model.TableDescription;
import treeInfoModel.ColumnInfo;
import treeInfoModel.InfoModels;
import treeInfoModel.TableInfo;

public class TableNode extends DefaultMutableTreeNode implements TreeNodeOverrides, Observer {
	private ArrayList<ColumnNode> columns= new ArrayList<>();
	private TableInfo info;
	private transient MutableTreeNode parent=null;
	private TableDescription desciption;
	
	public TableInfo getInfo() {
		return info;
	}
	public void setInfo(TableInfo info) {
		this.info = info;
	}
	public TableNode(TableInfo k) {
		AppCore.getInstance().getTree().subscribe(this);
		info=k;
	}
	public TableNode() {
		AppCore.getInstance().getTree().subscribe(this);
		info=new TableInfo();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addChild(InfoModels m) {
		if(m instanceof ColumnInfo){
			ColumnNode k=new ColumnNode((ColumnInfo)m);
			k.setParent(this);
			columns.add(k);
			//info.add
		}
		
	}
	


	@Override
	public void addChild(DefaultMutableTreeNode m) {
		if(m instanceof ColumnNode){
			m.setParent(this);
			columns.add((ColumnNode)m);
		}
		
	}
	@Override
	public TreeNode getParent() {
		return parent;
	}
	@Override
	public void setParent(MutableTreeNode arg0) {
		
		// TODO Auto-generated method stub
		parent=arg0;
	}
	@Override
	public String toString(){
		return info.getName();
	}
	public ArrayList<ColumnNode> getColumns() {
		return columns;
	}
	
	public boolean getAllowsChildren(){return true;}
	public void remove(MutableTreeNode aChild){columns.remove(aChild);}

	public void remove(int childIndex) {columns.remove(childIndex);}

	public void removeAllChildren(){columns.clear();}

	public TreeNode getChildAt(int index){return columns.get(index);}

	public boolean isLeaf(){return false;}
	
	public int getChildCount(){return columns.size();}
	
	public void add(MutableTreeNode newChild) {
		if(newChild instanceof DefaultMutableTreeNode)
		addChild((DefaultMutableTreeNode)newChild);
	}
	public void setDescription(TableDescription tableDescription) {
		this.desciption = tableDescription;
		
	}
	public TableDescription getDesciption() {
		return desciption;
	}
	@Override
	public void syncState(Observed subject) {
		System.out.println("Revalidating "+this.toString());
		
		ArrayList<ForeignTableBundle> newest= ((WorkspaceNode)subject).getNewestVersionOfDB();
		
		//parent.remove(this);
		//((DefaultTreeModel)AppCore.getInstance().getMainFrame().getLeft().getJTree().getModel()).removeNodeFromParent(this);
		
	}
	
}
