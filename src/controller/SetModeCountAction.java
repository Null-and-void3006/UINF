package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;
import model.Mode;

public class SetModeCountAction extends AbstractAction{
	
	public SetModeCountAction() {
		putValue(NAME, "Count");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		AppCore.getInstance().setState(Mode.COUNT);
	}

}
