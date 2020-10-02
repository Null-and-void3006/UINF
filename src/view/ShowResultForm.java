package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Record;
import net.miginfocom.swing.MigLayout;

public class ShowResultForm extends JPanel{
	
	public ShowResultForm(Record record) {
		// TODO Auto-generated constructor stub
		super(new MigLayout());
		for(String value : record.getValues()){
			JTextField temp = new JTextField(value);
			temp.setEditable(false);
			this.add(temp,"wrap");
		}
		this.setVisible(true);
	}

}
