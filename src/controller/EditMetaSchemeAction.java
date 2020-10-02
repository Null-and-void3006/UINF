package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;
import model.TableInfoStorage;
import view.EditorDialog;

public class EditMetaSchemeAction extends AbstractAction{
	private TableInfoStorage meta;
	
	public EditMetaSchemeAction() {
		this.putValue(NAME, "Edit MetaScheme");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new EditorDialog(AppCore.getInstance().getCurrMeta());
	}

}
