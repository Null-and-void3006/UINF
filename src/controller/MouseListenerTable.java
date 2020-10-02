package controller;

import java.awt.event.MouseAdapter;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.ExpandVetoException;

import app.AppCore;
import treeNodeModel.TableNode;

public class MouseListenerTable extends MouseAdapter{
	
	/*public void treeWillExpand(TreeExpansionEvent arg0) throws ExpandVetoException {
		if(AppCore.getInstance().getMainFrame().getLeft().getSelection() instanceof TableNode){
			throw new ExpandVetoException(arg0);
		}
		
	}*/

}
