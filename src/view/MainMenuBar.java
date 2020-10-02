package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import app.AppCore;
import controller.ActionManager;
import controller.EditMetaSchemeAction;

public class MainMenuBar extends JMenuBar{
	private JMenu help;
	private JMenu file;
	private JMenu edit;
	
	public MainMenuBar()
	{
		help = new JMenu("Help");
		file = new JMenu("File");
		edit = new JMenu("Edit");
		
		file.add(ActionManager.getInstance().getBrowseForMetaAction());
		edit.add(new EditMetaSchemeAction());
		help.add(ActionManager.getInstance().getAboutAction());
		
		this.add(file);
		this.add(edit);
		this.add(help);
	}
}
