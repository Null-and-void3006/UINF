package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;
import model.Mode;

public class SetModeEditAction extends AbstractAction{
	
	public SetModeEditAction() {
		putValue(NAME, "Update Record");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AppCore.getInstance().setState(Mode.EDIT);
	}

}
