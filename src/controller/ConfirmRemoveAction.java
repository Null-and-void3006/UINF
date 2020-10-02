package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;
import model.Record;
import model.SequenceFile;
import view.RemoveRecordForm;

public class ConfirmRemoveAction extends AbstractAction {
	RemoveRecordForm frame;
	public ConfirmRemoveAction(RemoveRecordForm frame) {
		putValue(NAME, "Confirm");
		
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Record r = new Record();
		String key = "";
		for (int i = 0; i < frame.getValues().size(); i++) { 
			r.addValue(frame.getValues().get(i).getText());
		}
		((SequenceFile)AppCore.getInstance().getCurrentTable().getFile()).deleteRecord(r);
	}

}
