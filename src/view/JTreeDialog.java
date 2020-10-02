package view;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

public class JTreeDialog extends CustomJDialog{
	public JTreeDialog(JTree tree) {
		super();
		setTitle("Tree");
		this.setSize(450, 700);
		this.setLayout(new BorderLayout());
		
		
		
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.add(new JScrollPane(tree), BorderLayout.CENTER);
		
		this.setVisible(true);
	}

}
