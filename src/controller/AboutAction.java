package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;

public class AboutAction extends AbstractAction{
	public AboutAction() {

		putValue(NAME, "About us");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		AppCore.getInstance().seeAbout();
		
	}
}
