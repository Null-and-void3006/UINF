package controller;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import app.AppCore;
import app.RepositoryCommander;
import view.ExceptionFrame;

public class CalculateAvgAction extends AbstractAction{
	String avg;
	JTextArea group;
	ResultSet result;
	JTable table;
	
	public CalculateAvgAction(String s, JTextArea group) {
		putValue(NAME, "Calculate Average for "+s);
		this.avg = s;
		this.group = group;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String query = "";
		query+= "SELECT AVG(["+avg+"]) as "+avg+" ";
		System.out.println(group.getText());
		String[] groups = group.getText().split("\n");
		for(int i=0;i<groups.length;i++){
			query+= ", cast( "+groups[i]+" as nvarchar(max)) as "+groups[i]+" ";
		}
		query+= " FROM "+ AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo().getName()
				+ " GROUP BY ";
		for(int i=0;i<groups.length;i++){
			query+= "cast( "+groups[i]+" as nvarchar(max))";
		}
		System.out.println(query);
		try {
			result = RepositoryCommander.getInstance().doQuery(query);
			
			System.out.println(result.getMetaData().getColumnName(1));
			System.out.println(result.getMetaData().getColumnName(2));
			String[][] data = new String[100][groups.length+1];
			String[] header = new String[groups.length+1];
			header[0] = avg;
			for(int i=1; i<groups.length+1;i++){
				header[i] = groups[i-1];
			}
			int cnt=0;
			while(result.next()){
				for(int i=0;i<=header.length-1;i++){
					data[cnt][i] = result.getString(header[i]);
				}
				cnt++;
			}
			table = new JTable(data, header);
			//AppCore.getInstance().getMainFrame().getPanelRight().remove(AppCore.getInstance().getMainFrame().getPanelRight().getTable());
			AppCore.getInstance().getMainFrame().getPanelRight().swapTable(table);
			AppCore.getInstance().getMainFrame().revalidate();
			AppCore.getInstance().getMainFrame().repaint();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			ExceptionFrame frame = new ExceptionFrame(4,e1);
			//e.printStackTrace();
		}
	}
}