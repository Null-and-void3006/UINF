package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import app.DialogHandler;
import view.CustomJDialog;

public class CloseAction extends AbstractAction{
	public CloseAction() {
		
		putValue(NAME, "Cancel");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DialogHandler.getInstance().closeCurrentDialog();
	}
}
