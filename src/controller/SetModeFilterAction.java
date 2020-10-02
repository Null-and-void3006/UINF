package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;
import model.Mode;

public class SetModeFilterAction extends AbstractAction{
	
	public SetModeFilterAction() {
		putValue(NAME, "Filter");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AppCore.getInstance().setState(Mode.FILTER);
	}

}
