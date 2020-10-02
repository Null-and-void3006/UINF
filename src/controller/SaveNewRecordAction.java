package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import app.AppCore;
import app.DialogHandler;
import app.RepositoryCommander;
import fileHandling.FileSystemHandler;
import model.FileOps;
import model.Record;
import model.SequenceFile;
import treeNodeModel.ColumnNode;
import treeNodeModel.TableNode;
import view.AddRecordForm;
import view.ExceptionFrame;

public class SaveNewRecordAction extends AbstractAction{
	AddRecordForm frame;
	
	public SaveNewRecordAction(AddRecordForm frame) {
		putValue(NAME, "Confirm");
		
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Record r = new Record();
		String key = "";
		for (int i = 0; i < frame.getValues().size(); i++) {
			if(frame.getValues().get(i).getText().length()!=((ColumnNode)AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getChildAt(i))
					.getInfo().getField().getLength()) frame.getValues().get(i).setText(fillBlanks(frame.getValues().get(i).getText(),
							(ColumnNode)AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getChildAt(i)));
			r.addValue(frame.getValues().get(i).getText());
		}
		for (int i = 0; i < frame.getValues().size(); i++) {
			if(frame.getMeta().getTable().getColumns().get(i).getInfo().getField().isPK()) key+=frame.getValues().get(i);
		}
		r.setKey(key);
		if(AppCore.ONLINE){
			StringBuilder query = new StringBuilder("INSERT INTO ");
			query.append(AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo().getName());
			query.append(" (");
			ArrayList<ColumnNode> columns =  AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getColumns();
			for(int i=0;i<columns.size();i++){
				query.append("[");
				query.append(columns.get(i).toString());
				query.append("]");
				if(i<columns.size()-1) query.append(",");
			}
			query.append(") VALUES (");
			for(int i=0;i<r.getValues().size();i++){
				query.append("'");
				query.append(r.getValues().get(i).toString());
				query.append("'");
				if(i<r.getValues().size()-1) query.append(",");
			}
			query.append(")");
			
			try {
				RepositoryCommander.getInstance().doQuery(query.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				ExceptionFrame frame = new ExceptionFrame(0,e);
				//e.printStackTrace();
			} finally {
				AppCore.getInstance().getTree().triggetChange();
				AppCore.getInstance().hardReset();
			}
			
			return;
			
		}
		
		((FileOps)AppCore.getInstance().getCurrentTable().getFile()).addRecord(r);
		
		String backupPath = (System.getProperty("user.dir")+"\\"+(new File(AppCore.getInstance().getCurrentTable().getTable().getInfo().getPath())).getName())+".backup";
		File backup = new File(backupPath);
		if(!backup.exists()){
			try {
				backup.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			File src = new File(AppCore.getInstance().getCurrentTable().getTable().getInfo().getPath());
			FileSystemHandler.copyContents(src,backup);
		}
		FileSystemHandler.appendLine(backup, r.toString());
		
		
	}
	
	private String fillBlanks(String s, ColumnNode node){
		for(int i=s.length();i<node.getInfo().getField().getLength();i++){
			s+=" ";
			
		}
		return s;
	}

}
