package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import app.AppCore;
import controller.AddRecordAction;
import controller.MainFrameListener;
import controller.RemoveRecordAction;
import controller.SetModeAvgAction;
import controller.SetModeCountAction;
import controller.SetModeEditAction;
import controller.SetModeFilterAction;
import controller.SetModeFindAction;
import controller.SetModeSortAction;
import controller.ShowRelationViewAction;
import controller.UpdateRecordAction;
import treeNodeModel.WorkspaceNode;

public class MainFrame extends JFrame{
	private JSplitPane splitPane;
	private JSplitPane infoSplitter;
	private JTreePanel panelLeft;
	private TablePanel panelRight;
	private StatefulPanel infoPanel;
	private ArrayList<JFrame> docs;
	private JTable table;
	private JButton halp;
	private JButton halp2;
	private JButton find;
	private JButton relation;
	private JButton edit;
	private JToolBar toolbar;
	private JButton average;
	private JButton count;
	private JButton filter;
	private JButton sort;
	
	private MainMenuBar mb;
	
	private InfoToolbar tb;
	
	private JPanel panel;
	
	public MainFrame()
	{
		super("RuInfRe 205.4");
		this.setSize(1300, 900);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		halp=new JButton("DEW IT");
		halp2=new JButton("DEW IT");
		find = new JButton("Find Record");
		relation = new JButton("Relation View");
		edit = new JButton("Edit Record");
		average = new JButton("AVG");
		count = new JButton("Count");
		filter = new JButton("Filter");
		sort = new JButton("Sort");
		toolbar = new JToolBar();
		
		panelLeft=new JTreePanel(AppCore.getInstance().getTree());
		infoPanel = new StatefulPanel();
		panelRight = new TablePanel(AppCore.getInstance().getCurrentTable().getTable());
		
		halp.setAction(new AddRecordAction());
		toolbar.add(halp);
		halp2.setAction(new RemoveRecordAction());
		toolbar.add(halp2);
		relation.setAction(new ShowRelationViewAction());
		toolbar.add(relation);
		find.setAction(new SetModeFindAction());
		toolbar.add(find);
		edit.setAction(new SetModeEditAction());
		toolbar.add(edit);
		average.setAction(new SetModeAvgAction());
		toolbar.add(average);
		count.setAction(new SetModeCountAction());
		toolbar.add(count);
		filter.setAction(new SetModeFilterAction());
		toolbar.add(filter);
		sort.setAction(new SetModeSortAction());
		toolbar.add(sort);
		toolbar.setVisible(true);
		panelRight.add(toolbar);
		mb = new MainMenuBar();
		tb = new InfoToolbar();
		
		
		infoSplitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT,panelRight,infoPanel);
		splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelLeft,infoSplitter);
		splitPane.setDividerLocation(255);
		
		panel = new JPanel(new BorderLayout());
		panel.add(splitPane,BorderLayout.CENTER);
		panel.setVisible(true);
		
		if(!AppCore.getInstance().ONLINE)this.addWindowListener(new MainFrameListener());
		this.setJMenuBar(mb);
		this.add(tb);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setVisible(true);
		
		//this.pack();
		
	}

	public TablePanel getPanelRight() {
		return panelRight;
	}
	

	
	
	/*public void closeInternals(){
		for(JInternalFrame i: panelRight.getAllFrames()){
			if(!i.isDisplayable())continue;
			i.dispose();
		}
	}*/
	
	
	public WorkspaceNode getRoot(){
		return (WorkspaceNode) panelLeft.getJTree().getModel().getRoot();
	}

	public JTreePanel getLeft() {
		// TODO Auto-generated method stub
		return panelLeft;
	}
	public void updateStateful(){
		infoPanel.Refresh();
		infoPanel.revalidate();
	}
	
	public void constructorReset(){
		
		this.getContentPane().removeAll();
		
		halp=new JButton("DEW IT");
		halp2=new JButton("DEW IT");
		find = new JButton("Find Record");
		relation = new JButton("Relation View");
		edit = new JButton("Edit Record");
		average = new JButton("AVG");
		count = new JButton("Count");
		filter = new JButton("Filter");
		sort = new JButton("Sort");
		toolbar = new JToolBar();
		
		panelLeft=new JTreePanel(AppCore.getInstance().getTree());
		infoPanel = new StatefulPanel();
		panelRight = new TablePanel(AppCore.getInstance().getCurrentTable().getTable());
		
		halp.setAction(new AddRecordAction());
		toolbar.add(halp);
		halp2.setAction(new RemoveRecordAction());
		toolbar.add(halp2);
		relation.setAction(new ShowRelationViewAction());
		toolbar.add(relation);
		find.setAction(new SetModeFindAction());
		toolbar.add(find);
		edit.setAction(new SetModeEditAction());
		toolbar.add(edit);
		average.setAction(new SetModeAvgAction());
		toolbar.add(average);
		count.setAction(new SetModeCountAction());
		toolbar.add(count);
		filter.setAction(new SetModeFilterAction());
		toolbar.add(filter);
		sort.setAction(new SetModeSortAction());
		toolbar.add(sort);
		toolbar.setVisible(true);
		panelRight.add(toolbar);
		mb = new MainMenuBar();
		tb = new InfoToolbar();
		
		
		infoSplitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT,panelRight,infoPanel);
		splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelLeft,infoSplitter);
		splitPane.setDividerLocation(255);
		
		panel = new JPanel(new BorderLayout());
		panel.add(splitPane,BorderLayout.CENTER);
		panel.setVisible(true);
		
		if(!AppCore.getInstance().ONLINE)this.addWindowListener(new MainFrameListener());
		this.setJMenuBar(mb);
		this.add(tb);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setVisible(true);
		this.repaint();
		
	}
	public void addFilteredTable(JTable table){
		panelRight.add(table);
	}
	
}
