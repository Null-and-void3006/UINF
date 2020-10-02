package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.DialogHandler;
import view.EditorDialog;

public class EditorSaveAction extends AbstractAction{

private EditorDialog ed;
	
	public EditorSaveAction(EditorDialog d) {
		putValue(NAME, "Save");
		ed = d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ed.save();
		DialogHandler.getInstance().closeCurrentDialog();
	}
	
}
