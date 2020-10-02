package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import app.AppCore;
import controller.CloseAction;
import controller.SaveNewRecordAction;
import model.DataFile;
import model.TableInfoStorage;
import model.Record;
import model.TableDescription;
import net.miginfocom.swing.MigLayout;

public class AddRecordForm extends JPanel{
	private ArrayList<JTextField> values;
	private JButton confirm;
	private JButton cancel;
	private TableDescription meta;
	private Record record;
	private JPanel panel;
	public AddRecordForm(Record r) {
		super();
		panel = new JPanel(new MigLayout());
		this.setSize(new Dimension(500, 500));
		this.add(panel);
		record = r;
		meta = AppCore.getInstance().getCurrentTable();
		System.out.println(meta.getNumFields());
		values=new ArrayList<>();
		// TODO Auto-generated constructor stub
		for(int i=0;i<meta.getNumFields();i++){
			JLabel lb=new JLabel(meta.getTable().getChildAt(i).toString());
			panel.add(lb,"wrap");
			JTextField text=new JTextField(40);
			panel.add(text,"wrap");
			values.add(text);
		}
		confirm= new JButton("Confirm");
		cancel= new JButton("Cancel");
		confirm.setAction(new SaveNewRecordAction(this));
		cancel.setAction(new CloseAction());
		panel.add(confirm,"wrap");
		panel.add(cancel,"wrap");
		panel.setVisible(true);
		System.out.println("here");
		this.setVisible(true);
		System.out.println(AppCore.getInstance().getCurrMeta().getFile());
		
	}
	public ArrayList<JTextField> getValues() {
		return values;
	}
	public TableDescription getMeta() {
		// TODO Auto-generated method stub
		return meta;
	}
	public Record getRecord(){
		return record;
	}
	
	
	

}
