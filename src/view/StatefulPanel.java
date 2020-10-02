package view;

import javax.swing.JPanel;

import app.AppCore;
import model.Record;

public class StatefulPanel extends JPanel{
	
	
	public StatefulPanel() {
		super();
		Refresh();
		this.setVisible(true);
	}
	
	public void Refresh(){
		this.removeAll();
		switch(AppCore.getInstance().getState()){
		case ADD:
			this.add(new AddRecordForm(new Record()));
			break;
		case BROWSE:
			this.add(new RelationViewForm());
			break;
		case FIND:
			this.add(new SearchForm());
			break;
		case SHOW:
			this.add(new ShowResultForm(AppCore.getInstance().foundRecord()));
			break;
		case EDIT:
			this.add(new EditForm());
			break;
		case AVG:
			this.add(new AvgForm());
			break;
		case COUNT:
			this.add(new CountForm());
			break;
		case FILTER:
			this.add(new FilterTableForm());
			break;
		case SORT:
			this.add(new SortForm());
			break;
			
		}
		System.out.println(AppCore.getInstance().getState());
		this.revalidate();
		this.repaint();
	}

}
