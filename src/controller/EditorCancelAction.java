package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.EditorDialog;

public class EditorCancelAction extends AbstractAction{

	private EditorDialog ed;
	
	public EditorCancelAction(EditorDialog d) {
		putValue(NAME, "Cancel");
		ed = d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ed.dispose();
	}

}
