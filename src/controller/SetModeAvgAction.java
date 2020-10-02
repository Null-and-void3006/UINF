package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;
import model.Mode;

public class SetModeAvgAction extends AbstractAction{
	
	public SetModeAvgAction() {
		putValue(NAME, "Average");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AppCore.getInstance().setState(Mode.AVG);
	}

}
