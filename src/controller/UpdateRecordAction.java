package controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import app.AppCore;
import app.RepositoryCommander;
import model.Record;
import treeNodeModel.ColumnNode;
import view.EditForm;
import view.ExceptionFrame;

public class UpdateRecordAction extends AbstractAction{
	EditForm form;

	public UpdateRecordAction(EditForm form) {
		putValue(NAME, "Update Record");
		this.form = form;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = AppCore.getInstance().getMainFrame().getPanelRight().getTable().getSelectedRow();
		Record record = AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo().getRecords().get(index);
		ArrayList<String> values = new ArrayList<>();
		for(int i=0;i<form.getValues().size();i++){values.add(form.getValues().get(i).getText());}
		record.setValues(values);
		String query;
		for(int i=0;i<values.size();i++){
		query = "UPDATE "+
				form.getMeta().getTable().getInfo().getName()+
				" SET ["+
				((ColumnNode)form.getMeta().getTable().getChildAt(i)).getInfo().getName()+
				"] = '"+
				values.get(i)+
				"' WHERE "+
				((ColumnNode)form.getMeta().getTable().getChildAt(0)).getInfo().getName() +
				" = '" +
				values.get(0)+"'";
		try {
			System.out.println(query);
			RepositoryCommander.getInstance().doQuery(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
				ExceptionFrame frame = new ExceptionFrame(2,e1);
			
		}
		finally{
			AppCore.getInstance().getTree().triggetChange();
			AppCore.getInstance().hardReset();
		}
		}
	}

}
