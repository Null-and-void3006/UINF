package view;

import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import app.AppCore;
import app.DialogHandler;
import controller.DialogListener;

public class CustomJDialog extends JDialog{
public CustomJDialog() {
	DialogHandler.getInstance().pushDialog(this);
	this.setResizable(false);
	this.addWindowListener(new DialogListener());
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setModalityType(ModalityType.APPLICATION_MODAL);
}
}
