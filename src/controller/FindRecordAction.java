package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;

import org.w3c.dom.views.AbstractView;

import app.AppCore;
import model.Mode;
import model.Record;
import treeInfoModel.TableInfo;
import treeNodeModel.IRNode;
import treeNodeModel.TableNode;
import view.SearchForm;

public class FindRecordAction extends AbstractAction {
	private SearchForm form;
	private int index;
	private Record record;
	private String key;
	private int cnt;
	private int found;
	
	public FindRecordAction(SearchForm form) {
		// TODO Auto-generated constructor stub
		this.form = form;
		putValue(NAME, "Find");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			index = form.getIndex();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			index = 0;
		}
		
		if(!form.binarySearch()){
		record = new Record();
		cnt = 0;
		key = new String();
		found = -1;
		for(JTextField field : form.getvalues()){
			record.addValue(field.getText());
			if(AppCore.getInstance().getCurrentTable().getTable().getColumns().get(cnt).getInfo().getField().isPK()) key+=field.getText();
			cnt++;
		}
		record.setKey(key);
		cnt = 0;
		for(Record rec : AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo().getRecords()){
			if(cnt >= index){
			if(rec.getKey().equals(record.getKey())) found = cnt;}
			cnt++;
		}
		/* If the proper record has not been found, check if you need to add white spaces to preserve meta scheme integrity */
		/* TODO: Figure out where we want to display the found record
		 * Options:
		 * Inside the stateful panel 
		 * As a separate frame
		 * Show the database table starting with the found record*/
		
		AppCore.getInstance().setRecord(AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo()
		.getRecords().get(found));
		AppCore.getInstance().setState(Mode.SHOW);
	}

		else{
			record = new Record();
			key = new String();
			
			for(JTextField field : form.getvalues()){
				record.addValue(field.getText());
				if(AppCore.getInstance().getCurrentTable().getTable().getColumns().get(cnt).getInfo().getField().isPK()) key+=field.getText();
				cnt++;
			}
			
			record.setKey(key);
			
			int low = index;
			int high = AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo()
			.getRecords().size();
			int mid = (low+high)/2;
			
			while(low<high){
				
				if(AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo()
			.getRecords().get(mid).getKey().compareTo(record.toString())==0){
							break;
				}
				if(AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo()
						.getRecords().get(mid).getKey().compareTo(record.getKey())==-1){
					low = mid;
					mid = (low+high)/2;
					
				}
				else{
					high = mid;
					mid = (low+high)/2;
				}
				
				
				
			}
			
			
			
			AppCore.getInstance().setRecord(AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo()
					.getRecords().get(mid));
			AppCore.getInstance().setState(Mode.SHOW);
		}


}
}
