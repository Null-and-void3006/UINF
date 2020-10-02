package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.AppCore;
import controller.ActionManager;
import net.miginfocom.swing.MigLayout;

//import net.miginfocom.swing.MigLayout;

public class AboutDialog extends CustomJDialog{
	

	private JPanel panel;
	
	private JLabel lbND;
	private JLabel lbSS;
	private JLabel lbLN;
	private JLabel lbMB;
	
	private JButton btOK;
	
	public AboutDialog()
	{
		super();
		this.setSize(450, 700);
		this.setResizable(false);
		this.setTitle("About us");
		
		panel = new JPanel(new MigLayout());
		
		//btOK=new JButton(ActionManager.getInstance().getCloseDialogAction());
	//	btOK.setText("Close");
		
		
		lbND = new JLabel("Nikola Djurovic, RN04/16 (team leader)");
		lbMB = new JLabel("Mihailo Bulajic, RN05/16");
		lbLN = new JLabel("Lazar Nestorovic, RN45/16");
		
		lbND.setIcon(new ImageIcon("res/img/NDj1.jpg"));
		lbMB.setIcon(new ImageIcon("res/img/MB.jpg"));
		lbLN.setIcon(new ImageIcon("res/img/LN.jpg"));
		
		panel.add(lbND);
		panel.add(lbMB);
		panel.add(lbLN,"wrap");
		//panel.add(btOK);
		
		
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		//this.setModal(true);
		this.setVisible(true);
	}
}
