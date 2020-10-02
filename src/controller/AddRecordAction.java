package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

import app.AppCore;
import model.Mode;
import model.Record;
import model.SequenceFile;
import model.SerialFile;
import view.AddRecordForm;

public class AddRecordAction extends AbstractAction{
	public AddRecordAction() {
		// TODO Auto-generated constructor stub
		this.putValue(NAME, "Add Record");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		AppCore.getInstance().setState(Mode.ADD);
		Record r=new Record();
		new AddRecordForm(r);
		//SwingUtilities.updateComponentTreeUI(AppCore.getInstance().getMainFrame().getLeft().getJTree());
		//new AddRecordForm(AppCore.getInstance().getCurrMeta());
	}

}
