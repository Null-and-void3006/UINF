package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;

public class NextAction extends AbstractAction{
	
public  NextAction() {
	super();
	putValue(NAME,"Next");
}
	@Override
	public void actionPerformed(ActionEvent e) {
		AppCore.getInstance().getMainFrame().getPanelRight().nextBlock();
		
	}

}
