package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CloseAction;
import controller.ConfirmRemoveAction;
import controller.RemoveRecordAction;
import controller.SaveNewRecordAction;
import model.TableInfoStorage;
import net.miginfocom.swing.MigLayout;

public class RemoveRecordForm extends CustomJDialog{
	private ArrayList<JTextField> values;
	private JButton confirm;
	private JButton cancel;
	private JPanel panel;
	private TableInfoStorage meta;
	
	public RemoveRecordForm(TableInfoStorage meta) {
		super();
		this.setSize(500, 500);
		this.meta = meta;
		values=new ArrayList<>();
		
		panel = new JPanel(new MigLayout());
		System.out.println(meta.getNumFields());
		for(int i=0;i<meta.getTable().getColumns().size();i++){
			JLabel lb=new JLabel(meta.getTable().getChildAt(i).toString());
			panel.add(lb);
			JTextField text=new JTextField(40);
			panel.add(text, "wrap");
			values.add(text);
			
		}
		confirm= new JButton("Confirm");
		cancel= new JButton("Cancel");
		confirm.setAction(new ConfirmRemoveAction(this));
		cancel.setAction(new CloseAction());
		panel.add(confirm);
		panel.add(cancel);
		this.add(panel);
		this.setVisible(true);
	}
	public ArrayList<JTextField> getValues() {
		return values;
	}
	public JButton getConfirm() {
		return confirm;
	}
	public JButton getCancel() {
		return cancel;
	}
	public JPanel getPanel() {
		return panel;
	}
	public TableInfoStorage getMeta() {
		return meta;
	}
}
