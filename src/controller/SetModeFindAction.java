package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

import app.AppCore;
import model.Mode;

public class SetModeFindAction extends AbstractAction {
	public SetModeFindAction() {
		putValue(NAME, "Find Record");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AppCore.getInstance().setState(Mode.FIND);
		
	}


}
