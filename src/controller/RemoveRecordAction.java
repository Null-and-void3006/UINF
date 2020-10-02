package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import app.AppCore;
import app.RepositoryCommander;
import fileHandling.FileSystemHandler;
import model.Record;
import model.SequenceFile;
import treeNodeModel.TableNode;
import view.ExceptionFrame;



public class RemoveRecordAction extends AbstractAction{
	public RemoveRecordAction() {
		// TODO Auto-generated constructor stub
		this.putValue(NAME, "Remove Record");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = AppCore.getInstance().getMainFrame().getPanelRight().getTable().getSelectedRow();
		if(index == -1)return;
		Record obj = AppCore.getInstance().getMainFrame().getPanelRight().getCurrentRecord();
		if(AppCore.ONLINE){
			StringBuilder query = new StringBuilder("DELETE FROM ");
			query.append(AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo().getName());
			query.append(" WHERE ");
			query.append(AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getColumns().get(0));
			query.append("=");
			query.append(obj.getValues().get(0));
			System.out.println(query);
			try {
				RepositoryCommander.getInstance().doQuery(query.toString());
				
			} catch (SQLException e1) {
				if(e1.getMessage() != "The statement did not return a result set."){
				ExceptionFrame removeException = new ExceptionFrame(1,e1);
				}
			}
			finally{
				AppCore.getInstance().getTree().triggetChange();
				AppCore.getInstance().hardReset();
			}
			
			
			return;
		}
		
		((DefaultTableModel)(AppCore.getInstance().getMainFrame().getPanelRight().getTable().getModel())).removeRow(index);

		
		AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo().getRecords().remove(obj);

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
		
		FileSystemHandler.removeLine(backup, obj.toString());
		
		
	}

}
