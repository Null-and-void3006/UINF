package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import app.AppCore;
import controller.ActionManager;
import fileHandling.FileSystemHandler;
import model.Record;
import net.miginfocom.swing.MigLayout;
import treeNodeModel.TableNode;


public class TablePanel extends JPanel{
	
	JTable table;
	String[] kolone;
	String[][] informacije;
	TableNode tbn;
	int blockIndex=0;
	JTextField tfNumOfRows=new JTextField("25");
	JButton btPrevious= new JButton(ActionManager.getInstance().getPreviousAction());
	JButton btNext=new JButton(ActionManager.getInstance().getNextAction());
	JPanel helperPanel = new JPanel();
	

	
	public TablePanel(TableNode tbn) {
		
		if(tbn.getInfo().getRecords().size() < 25) tfNumOfRows.setText(tbn.getInfo().getRecords().size()+"");
		initKolone(tbn);
		
		table = new JTable(fetchBlock(getRows(),true)){public boolean isCellEditable(int rowIndex, int colIndex) {return false;}};
		this.setLayout(new MigLayout());
		this.add(table.getTableHeader(), "wrap");

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		this.add(btPrevious,"split 3");
		this.add(tfNumOfRows);
		this.add(btNext,"wrap");
		helperPanel.setLayout(new MigLayout());
		helperPanel.add(table.getTableHeader(), "wrap");
	
		helperPanel.add(table,"wrap");
		this.add(helperPanel, "wrap");
		tfNumOfRows.setPreferredSize(new Dimension(100, 25));
		table.setColumnSelectionAllowed(false);
	    table.setRowSelectionAllowed(true);
	    
	}
	public void replaceTable(TableNode tbn){initKolone(tbn);skipToStart();}
	
	public void initKolone(TableNode tbn){
		this.tbn = tbn;
		
		kolone=new String[tbn.getColumns().size()];
		
		for(int i=0; i<tbn.getColumns().size(); i++){
			kolone[i]=tbn.getColumns().get(i).getInfo().getName();
			
		}
		
		
	}
	
	public void swapTable(JTable table){
		helperPanel.removeAll();
		helperPanel.add(table.getTableHeader(),"wrap");
		helperPanel.add(table,"wrap");
		helperPanel.repaint();
	}
	
	public void restoreDefaultTable(){
		swapTable(getTable());
	}
	
	public void setTable(JTable table) {
		this.table = table;
	}
	public DefaultTableModel fetchBlock(int jmp,boolean dir){
		String[][] mtr=new String[jmp][kolone.length];
		
		if(!dir){blockIndex-=2*jmp;while/*if*/(blockIndex<0)blockIndex+=tbn.getInfo().getRecords().size();/*blockIndex=0;*/}
		try{
		for(int i=0;i<mtr.length;i++){
			for(int j=0;j<mtr[i].length;j++){
				mtr[i][j]=tbn.getInfo().getRecords().get( (i+blockIndex)%tbn.getInfo().getRecords().size() ).getValues().get(j);
			}
		}
		blockIndex+=jmp;
		
		
		blockIndex%=tbn.getInfo().getRecords().size();
		
		}
		catch(Exception e){System.out.println("HELP ME");}
		
		return new DefaultTableModel(mtr,kolone);
		
	}
	
	public Record getCurrentRecord(){
		int index;
		if((index = table.getSelectedRow()) == -1) return null;
		if(AppCore.ONLINE){			
			return tbn.getInfo().getRecords().get((index + blockIndex*getRows())%tbn.getInfo().getRecords().size());
		}
		return tbn.getInfo().getRecords().get( (index+blockIndex-getRows()) % tbn.getInfo().getRecords().size() );
	}
	
	public void nextBlock(){
		if(getRows() >= tbn.getInfo().getRecords().size()) return;
		table.setModel(fetchBlock(getRows(),true));
		
	}

	public void previousBlock() {
		if(getRows() >= tbn.getInfo().getRecords().size()) return;
		table.setModel(fetchBlock(getRows(),false));
		
	}
	public void skipToStart(){
		blockIndex=0;
		table.setModel(fetchBlock(getRows(),true));
	}
	
	public JTable getTable() {
		return table;
	}
	private int getRows(){return Integer.parseInt( tfNumOfRows.getText());}
	public TableNode getTbn() {
		return tbn;
	}
	
	
}
