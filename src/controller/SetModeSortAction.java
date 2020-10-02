package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;
import model.Mode;

public class SetModeSortAction extends AbstractAction{
	
	public SetModeSortAction() {
		putValue(NAME, "Sort");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AppCore.getInstance().setState(Mode.SORT);
	}

}
