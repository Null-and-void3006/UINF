package view;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import app.AppCore;
import net.miginfocom.swing.MigLayout;
import treeNodeModel.ColumnNode;
import treeNodeModel.IRNode;
import treeNodeModel.TableNode;

public class RelationViewForm extends JPanel {
	private JTable relationTable;
	private String[][] relationMatrix;
	private String[][] keyMatrix;
	private String[] columns;
	private TableNode table;
	private IRNode database;
	private int cnt;
	private int numberOfRelations;
	
	public RelationViewForm() {
		super(new MigLayout());
		database = (IRNode)AppCore.getInstance().getTree().getChildAt(0);
		cnt = 0;
		keyMatrix = new String[100][2];
		for(int i=0; i<database.getChildCount();i++){
			table = ((TableNode)database.getChildAt(i));
			for(int j=0;j<table.getChildCount();j++){
				
				if(((ColumnNode)table.getChildAt(j)).getInfo().getField().isPK()){
					keyMatrix[cnt][0] = table.getInfo().getName();
					keyMatrix[cnt][1] = ((ColumnNode)table.getChildAt(j)).getInfo().getField().getName();
					
					cnt++;
					
				}
			}
			/* TODO: Extract which fields are keys to build the keyMatrix
			 * TODO: Find which key fields in keyMatrix correspond to the same field in a different table and fill the relationMatrix */
		}
		numberOfRelations = 0;
		relationMatrix = new String[100][4];
		for(int i=0;i<cnt;i++){
			for(int j=i+1;j<cnt;j++){
				if(keyMatrix[i][1].equals(keyMatrix[j][1])){
					relationMatrix[numberOfRelations][0] = keyMatrix[i][0];
					relationMatrix[numberOfRelations][1] = keyMatrix[i][1];
					relationMatrix[numberOfRelations][2] = keyMatrix[j][1];
					relationMatrix[numberOfRelations][3] = keyMatrix[j][0];
					numberOfRelations++;
				}
			}
		}
		columns = new String[]{"Table","Field","Field","Table"};
		relationTable = new JTable(new DefaultTableModel(relationMatrix,columns));
		relationTable.setVisible(true);
		this.add(relationTable.getTableHeader(),"wrap");
		this.add(relationTable);
		this.setVisible(true);
	}

}
