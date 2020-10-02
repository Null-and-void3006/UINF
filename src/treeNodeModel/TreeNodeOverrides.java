package treeNodeModel;

import java.io.Serializable;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import treeInfoModel.InfoModels;

public interface TreeNodeOverrides extends Serializable {
public void addChild(InfoModels m);//Not default
	
	public void addChild(DefaultMutableTreeNode m);//Not default
	
	
	
	
	public TreeNode getParent();/**/

	public void setParent(MutableTreeNode newParent);/**/

	public String toString();/**/

	public boolean getAllowsChildren();
	
	//public Enumeration children();

	public void add(MutableTreeNode newChild);/**/
	
	public void remove(MutableTreeNode aChild);

	public void remove(int childIndex) ;

	public void removeAllChildren();

	public TreeNode getChildAt(int index);

	public boolean isLeaf();
	
	public int getChildCount();

	
	
	public int getIndex(TreeNode node);

}
