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

public class FilterTableForm extends JPanel{
	String tableName;
	ArrayList<JComponent> inputs;
	ArrayList<String> columns;
	JButton addButton;
	JButton runFilter;
	public FilterTableForm() {
		super();
		this.setLayout(new MigLayout());
		tableName = AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo().getName();
		
		inputs = new ArrayList<>();
		columns = new ArrayList<>();
		
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
		runFilter = new JButton("Run filter");
		runFilter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppCore.getInstance().putWhere(tableName, getValue());
				
			}
		});
		this.add(runFilter, "wrap");
		String[] y = {">","=", "<",">=","<=","LIKE"};
		String[] z = {"","NOT"};
		JComboBox<String> comparator = new JComboBox<>(y);
		JComboBox<String> negator = new JComboBox<>(z);
		JComboBox<String> column = new JComboBox<>(listToArray(columns));
		JTextField input = new JTextField();
		input.setPreferredSize(new Dimension(100, 25));
		
		inputs.add(negator);
		inputs.add(column);
		inputs.add(comparator);
		inputs.add(input);
		
		this.add(new Label("WHERE "));
		this.add(negator);
		this.add(column);
		this.add(comparator);
		this.add(input,"wrap");
		this.add(addButton);
	}
	
	private void addRow(){
		String[] x = {"AND", "OR"};
		String[] y = {">","=", "<",">=","<=","LIKE"};
		String[] z = {"","NOT"};
		JComboBox<String> connector = new JComboBox<>(x);
		JComboBox<String> comparator = new JComboBox<>(y);
		JComboBox<String> negator = new JComboBox<>(z);
		JComboBox<String> column = new JComboBox<>(listToArray(columns));
		JTextField input = new JTextField();
		input.setPreferredSize(new Dimension(100, 25));
		inputs.add(connector);
		inputs.add(negator);
		inputs.add(column);
		inputs.add(comparator);
		inputs.add(input);
		
		this.remove(addButton);
		this.add(connector);
		this.add(negator);
		this.add(column);
		this.add(comparator);
		this.add(input,"wrap");
		this.add(addButton);
		
		this.revalidate();
		this.repaint();	
	}
	
	private String getValue(){
		StringBuilder sb = new StringBuilder("WHERE ");
		for(JComponent jc: inputs){
			if(jc instanceof JComboBox<?>){
				JComboBox<String> cb= (JComboBox<String>)jc;
				
				if(((String)(cb.getSelectedItem())).compareTo("NOT") == 0 || ((String)(cb.getSelectedItem())).compareTo("") == 0){
					sb.append(" ( ");
				}
				sb.append(((String)(cb.getSelectedItem())));
				sb.append(" ");
			}
			else{
				JTextField tf = (JTextField)jc;
				sb.append("'");
				sb.append(tf.getText());
				sb.append("' ) ");
			}
		}
		
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
