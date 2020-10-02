package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;

public class PreviousAction extends AbstractAction{
	public  PreviousAction() {
		super();
		putValue(NAME,"Previous");
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			AppCore.getInstance().getMainFrame().getPanelRight().previousBlock();
			
		}

}
