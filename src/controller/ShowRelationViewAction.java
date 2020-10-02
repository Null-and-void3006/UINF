package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;
import model.Mode;

public class ShowRelationViewAction extends AbstractAction{
	public ShowRelationViewAction() {
		// TODO Auto-generated constructor stub
		putValue(NAME, "Relation View");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AppCore.getInstance().setState(Mode.BROWSE);
	}

}
