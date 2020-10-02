package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JOptionPane;

import app.AppCore;
import fileHandling.FileSystemHandler;
import treeInfoModel.TableInfo;

public class MainFrameListener implements WindowListener{

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save your changes?","Notice",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){

		  File f = new File(System.getProperty("user.dir"));
		  
		  for(TableInfo ti: AppCore.getInstance().getAllTableInfos()){
			  if(f.listFiles() != null){}
			  File original = new File(ti.getPath());
			  File backup = new File(f.getAbsolutePath()+"\\"+original.getName()+".backup");
			  if(backup.exists()){
				  FileSystemHandler.copyContents(backup, original);
				  }
		  }
		}
		
		
		File f = new File(System.getProperty("user.dir"));
		for(File fs:f.listFiles()){
			if(fs.getName().endsWith(".backup")) fs.delete();
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
