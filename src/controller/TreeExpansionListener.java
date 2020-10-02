package controller;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;

import app.AppCore;
import model.TableDescription;
import treeNodeModel.TableNode;


public class TreeExpansionListener implements TreeWillExpandListener{

	@Override
	public void treeWillCollapse(TreeExpansionEvent arg0) throws ExpandVetoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeWillExpand(TreeExpansionEvent arg0) throws ExpandVetoException {
		AppCore.getInstance().getMainFrame().getPanelRight().restoreDefaultTable();
		try{		
		if(AppCore.getInstance().getMainFrame().getLeft().getJTree().getSelectionPath().getLastPathComponent() instanceof TableNode){
			AppCore.getInstance().getMainFrame().getPanelRight().replaceTable((TableNode) AppCore.getInstance().getMainFrame().getLeft().getJTree().getSelectionPath().getLastPathComponent());
			AppCore.getInstance().setCurrentTable(((TableNode)AppCore.getInstance().getMainFrame().getLeft().getJTree().getSelectionPath().getLastPathComponent()).getDesciption());
			//throw new ExpandVetoException(arg0);
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
