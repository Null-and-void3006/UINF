package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ActionManager;
import net.miginfocom.swing.MigLayout;

public class OpenMetaDialog extends CustomBrowserDialog{
	
	JButton btCancel;
	JButton btOK;
	public OpenMetaDialog() {
		super(JFileChooser.FILES_ONLY);


		setTitle("Open Meta Scheme");
		JPanel panel=new JPanel(new MigLayout());

		
		btCancel=new JButton(ActionManager.getInstance().getCloseDialogAction());
		btCancel.setText("Cancel");
		
		btOK= new JButton(ActionManager.getInstance().getOpenMetaAction());
		btOK.setText("Confirm");
		
		tfTarget.setPreferredSize(new Dimension(300, 25));
		panel.add(new JLabel("Navigate to meta file"),"wrap");
		panel.add(tfTarget);
		panel.add(browseButton,"wrap");
		panel.add(btOK);
		panel.add(btCancel);
		
		setContentPane(panel);
		this.pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public String getPath(){
		return tfTarget.getText();
	}
}
