package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;

public class OpenMetaAction extends AbstractAction{

	@Override
	public void actionPerformed(ActionEvent e) {
		AppCore.getInstance().openMeta();
		
	}

}
