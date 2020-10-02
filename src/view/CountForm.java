package view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.AppCore;
import controller.CalculateAvgAction;
import controller.CalculateCountAction;
import controller.CloseAction;
import model.Record;
import model.TableDescription;
import net.miginfocom.swing.MigLayout;

public class CountForm extends JPanel{
	private ArrayList<JTextField> values;
	private JButton cancel;
	private TableDescription meta;
	private Record record;
	private JPanel panel;
	private JTextArea group;
	public CountForm() {
		super();
		panel = new JPanel(new MigLayout());
		this.setSize(new Dimension(500, 500));
		this.add(panel);
		meta = AppCore.getInstance().getCurrentTable();
		System.out.println(meta.getNumFields());
		values=new ArrayList<>();
		group = new JTextArea();
		group.setPreferredSize(new Dimension(300, 100));
		// TODO Auto-generated constructor stub
		for(int i=0;i<meta.getNumFields();i++){
			JLabel lb=new JLabel(meta.getTable().getChildAt(i).toString());
			panel.add(lb,"wrap");
			JButton average=new JButton("Count");
			average.setAction(new CalculateCountAction(lb.getText(),group));
			panel.add(average,"wrap");
			
		}
		panel.add(group,"wrap");
		cancel= new JButton("Cancel");
		cancel.setAction(new CloseAction());
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
