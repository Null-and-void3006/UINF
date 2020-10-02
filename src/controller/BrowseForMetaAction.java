package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.OpenMetaDialog;

public class BrowseForMetaAction extends AbstractAction{
public BrowseForMetaAction() {
	putValue(NAME, "Open Meta");
}
	@Override
	public void actionPerformed(ActionEvent e) {
		new OpenMetaDialog();
		
	}

}
