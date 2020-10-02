package treeNodeModel;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import app.AppCore;
import app.RepositoryMapper;
import controller.Observed;
import controller.Observer;
import model.ForeignTableBundle;
import treeInfoModel.IRInfo;
import treeInfoModel.InfoModels;
import treeInfoModel.WorkspaceInfo;

public class WorkspaceNode extends DefaultMutableTreeNode implements TreeNodeOverrides, Observed {
	private ArrayList<IRNode> resources= new ArrayList<>();
	private ArrayList<Observer> observers = new ArrayList<>();
	private ArrayList<ForeignTableBundle> newestVersionOfDB;
	private WorkspaceInfo info;
	
	public WorkspaceNode(WorkspaceInfo k) {
		info=k;
	}
	public WorkspaceNode() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addChild(InfoModels m) {
		if(m instanceof IRInfo){
			IRNode k=new IRNode((IRInfo)m);
			k.setParent(this);
			resources.add(k);
		}
		
	}
	@Override
	public void addChild(DefaultMutableTreeNode m) {
		if(m instanceof IRNode){
			m.setParent(this);
			resources.add((IRNode)m);
		}
		
	}
	public WorkspaceInfo getInfo() {
		return info;
	}
	public void setInfo(WorkspaceInfo info) {
		this.info = info;
	}
	@Override
	public String toString(){
		return info.getName();
	}
	
	public boolean getAllowsChildren(){return true;}
	public void remove(MutableTreeNode aChild){resources.remove(aChild);}

	public void remove(int childIndex) {resources.remove(childIndex);}

	public void removeAllChildren(){resources.clear();}

	public TreeNode getChildAt(int index){return resources.get(index);}

	public boolean isLeaf(){return false;}
	
	public int getChildCount(){return resources.size();}
	
	public void add(MutableTreeNode newChild) {
		if(newChild instanceof DefaultMutableTreeNode)
		addChild((DefaultMutableTreeNode)newChild);
	}
	@Override
	public void triggetChange() {
		//newestVersionOfDB = AppCore.getInstance().getRowsByTableName( RepositoryMapper.getInstance().getTables());
		
		for(Observer ob: observers){
			ob.syncState(this);
		}
		
		AppCore.getInstance().getMainFrame().getLeft().getJTree().revalidate();
		AppCore.getInstance().getMainFrame().getLeft().getJTree().repaint();
		
	}
	public ArrayList<ForeignTableBundle> getNewestVersionOfDB() {
		return newestVersionOfDB;
	}
	@Override
	public void subscribe(Observer ob) {
		observers.add(ob);
		
	}
	@Override
	public void unsubscribe(Observer ob) {
		observers.remove(ob);
		
	}
}
