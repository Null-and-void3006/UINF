package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.AppCore;
import fileHandling.SerializationHandler;
import model.TableInfoStorage;
import model.TableDescription;
import view.MainFrame;

public class CloseMainFrame extends AbstractAction{
	private MainFrame mf;
	public CloseMainFrame(MainFrame mf) {
		this.mf=mf;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(TableInfoStorage m:AppCore.getInstance().getSchemes()){
			for(TableDescription td:m.getTableDescriptions())
		SerializationHandler.writeObject(td.getPath(),td.getTable());
		
		}
		
	}

}
//TODO: FIX DIS SHEIT DZONE