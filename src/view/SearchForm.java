package view;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.AppCore;
import controller.FindRecordAction;
import model.TableInfoStorage;
import model.TableDescription;
import net.miginfocom.swing.MigLayout;

public class SearchForm extends JPanel {
	private TableDescription meta;
	private ArrayList<JTextField> values;
	private JButton find;
	private JTextField index;
	private JCheckBox binary;
	
	public SearchForm() {
		super(new MigLayout());
		meta = AppCore.getInstance().getCurrentTable();
		values = new ArrayList<>();
		for(int i=0;i<meta.getNumFields();i++){
			JLabel lb=new JLabel(meta.getTable().getChildAt(i).toString());
			this.add(lb,"wrap");
			JTextField text=new JTextField(40);
			this.add(text,"wrap");
			values.add(text);
		}
		index = new JTextField(10);
		find = new JButton("Find");
		find.setAction(new FindRecordAction(this));
		this.add(index, "wrap");
		this.add(find, "wrap");
		binary = new JCheckBox("Binary search");
		this.add(binary, "wrap");
		this.setVisible(true);
	}
	
	public int getIndex() throws Exception{
		try{
		return Integer.parseInt(index.getText());
		}
		catch(Exception e){
			throw new IllegalArgumentException();
		}
	}

	public ArrayList<JTextField> getvalues() {
		// TODO Auto-generated method stub
		return values;
	}
	public boolean binarySearch(){
		return binary.isSelected();
	}

}
