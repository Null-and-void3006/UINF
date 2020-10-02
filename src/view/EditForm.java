package view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.AppCore;
import controller.CloseAction;
import controller.UpdateRecordAction;
import model.Record;
import model.TableDescription;
import net.miginfocom.swing.MigLayout;

public class EditForm extends JPanel {
	
	private ArrayList<JTextField> values;
	private JButton confirm;
	private JButton cancel;
	private TableDescription meta;
	private Record record;
	private JPanel panel;
	public EditForm() {
		// TODO Auto-generated constructor stub
		super();
		panel = new JPanel(new MigLayout());
		this.setSize(new Dimension(500, 500));
		this.add(panel);
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
		confirm.setAction(new UpdateRecordAction(this));
		cancel.setAction(new CloseAction());
		panel.add(confirm,"wrap");
		panel.add(cancel,"wrap");
		panel.setVisible(true);
		System.out.println("here");
		this.setVisible(true);
		System.out.println(AppCore.getInstance().getCurrMeta().getFile());
		
	}
	public ArrayList<JTextField> getValues() {
		System.out.println("get value");
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
