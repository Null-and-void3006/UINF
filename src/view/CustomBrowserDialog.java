package view;

import javax.swing.JButton;
import javax.swing.JTextField;

import controller.BrowseForFileAction;

public class CustomBrowserDialog extends CustomJDialog{
	public JButton browseButton;
	public JTextField tfTarget;
	
	public CustomBrowserDialog(int sm) {
		super();
		tfTarget=new JTextField();
		tfTarget.setEditable(false);
		browseButton=new JButton(new BrowseForFileAction(sm));
		browseButton.setText("Browse");
	}

	public void acceptFilePath(String s){
		if(s!=null)tfTarget.setText(s);
	}
}
