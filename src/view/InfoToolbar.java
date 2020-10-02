package view;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JToolBar;

import controller.AddRecordAction;
import controller.NumberOfRowsAction;
import controller.RemoveRecordAction;
import controller.SetModeEditAction;
import controller.SortTableAction;
import controller.UpdateRecordAction;

public class InfoToolbar extends JToolBar{
	
	public InfoToolbar() {
		super();
		
		
		this.setVisible(true);
		
		this.add(new AddRecordAction());
		this.add(new RemoveRecordAction());
		this.add(new SetModeEditAction());
		this.add(new SortTableAction());
		
	}

}
