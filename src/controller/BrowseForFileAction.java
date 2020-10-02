package controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import app.AppCore;
import app.DialogHandler;
import view.CustomBrowserDialog;

public class BrowseForFileAction  extends AbstractAction{

	int chooserModel;
public BrowseForFileAction(int a) {
	chooserModel =a;
	
}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("C:\\javaworkspace\\RuInfRe"));
		fc.setApproveButtonText("OK");
		
		
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileSelectionMode(chooserModel);
		
		int returnValue = fc.showSaveDialog(null);
		File f=null;
		
		//if(chooserModel==JFileChooser.DIRECTORIES_ONLY) f=fc.getCurrentDirectory();
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			f=fc.getSelectedFile();
		}

		
	
		
		
		((CustomBrowserDialog)DialogHandler.getInstance().getCurrentDialog()).acceptFilePath(f==null?null:f.getAbsolutePath());
		
	}
}
