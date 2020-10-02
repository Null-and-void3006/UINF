
package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import controller.TreeExpansionListener;
import fileHandling.SerializationHandler;
import javax.swing.tree.DefaultTreeModel;



public class JTreePanel extends JPanel{
	
private JTree tree=null;

	public JTreePanel(DefaultMutableTreeNode tm) {
		this.setLayout(new BorderLayout());
		tree = new JTree(tm);
		tree.addTreeWillExpandListener(new TreeExpansionListener());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.add(new JScrollPane(tree), BorderLayout.CENTER);
		SerializationHandler.writeObject("fuckMe.tree", tree);
	}
	
	public JTree getJTree() {
		// TODO Auto-generated method stub
		return tree;
	}

	
	
	
	
}
