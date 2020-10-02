package view;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.AppCore;
import net.miginfocom.swing.MigLayout;
import treeNodeModel.ColumnNode;

public class SortForm extends JPanel{
	String tableName;
	ArrayList<JComboBox<String>> inputs;
	ArrayList<String> columns;
	JButton addButton;
	JButton runFilter;
	ArrayList<JComboBox<String>> ascendiboi;
	public SortForm() {
		super();
		this.setLayout(new MigLayout());
		tableName = AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo().getName();
		
		inputs = new ArrayList<>();
		columns = new ArrayList<>();
		ascendiboi = new ArrayList<>();
		
		for(ColumnNode cn: AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getColumns()){
			columns.add("["+cn.toString()+"]");
		}
		
		addButton = new JButton("Add another clause");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addRow();
				
			}
		});
		runFilter = new JButton("Run sort");
		runFilter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppCore.getInstance().putOrderBy(tableName, getValue());
				
			}
		});
		this.add(runFilter, "wrap");
		JComboBox<String> column = new JComboBox<>(listToArray(columns));
		JComboBox<String> scension = new JComboBox<>();
		scension.addItem("ASC");
		scension.addItem("DESC");
		
		inputs.add(column);
		ascendiboi.add(scension);
		
		this.add(new Label("Sort By "));
		this.add(column,"wrap");
		this.add(scension,"wrap");
		this.add(addButton);
	}
	
	private void addRow(){
		JComboBox<String> column = new JComboBox<>(listToArray(columns));
		JComboBox<String> scension = new JComboBox<>();
		scension.addItem("ASC");
		scension.addItem("DESC");
		inputs.add(column);
		ascendiboi.add(scension);

		
		this.remove(addButton);
		this.add(column,"wrap");
		this.add(scension, "wrap");
		this.add(addButton);
		
		this.revalidate();
		this.repaint();	
	}
	
	private String getValue(){
		StringBuilder sb = new StringBuilder("ORDER BY ");
		int cnt = 0;
		for(JComboBox<String> jc: inputs){
			sb.append("cast( "+jc.getSelectedItem()+" as varchar(max))");
			sb.append(" "+ascendiboi.get(cnt).getSelectedItem());
			sb.append(" ,");
			cnt++;
		}
		sb.delete(sb.length()-2, sb.length());
		
		return sb.toString();
	}
	
	private String[] listToArray(ArrayList<String> list){
		String[] array = new String[list.size()];
		for(int i=0;i<list.size();i++)
		{
			array[i] = list.get(i);
		}
		return array;
	}

}
