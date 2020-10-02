package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import app.AppCore;
import controller.EditorCancelAction;
import controller.EditorSaveAction;
import fileHandling.FileSystemHandler;
import model.TableInfoStorage;
import treeInfoModel.TableInfo;
import treeNodeModel.TableNode;

public class EditorDialog extends CustomJDialog {
	private JPanel panel;
	private JTextArea area;
	private JButton save;
	private JButton cancel;
	private TableInfoStorage meta;
	private TableInfoStorage oldMeta;
	
	public EditorDialog(TableInfoStorage meta) {
		//super(meta.getTable().getInfo().getName());
		this.setSize(600, 800);
		area = new JTextArea();
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		save.setAction(new EditorSaveAction(this));
		cancel.setAction(new EditorCancelAction(this));
		this.meta = new TableInfoStorage("Sekvencijalne datoteke\\NastavniPredmeti.sek");
		//this.meta.setContent(meta.getContent());
		//this.meta.setTable(meta.getTable());
		oldMeta = meta;
		panel = new JPanel();
		
		area.setText(constructString());
		area.setSize(100, 100);
		area.setPreferredSize(new Dimension(550, 500));
		panel.add(area);
		panel.add(cancel);
		panel.add(save);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	public void save(){
		//System.out.println("FUNCTION WUZ NUKED N SHIEEEEEEET");
		//AppCore.getInstance().changeCurrMeta(new MetaScheme(area.getText()));
	}
	
	public String constructString(){
		StringBuilder sb=new StringBuilder();
		for(TableNode ti: AppCore.getInstance().getAllTableNodes()){
			ArrayList<String> ar = FileSystemHandler.loadStringArray(ti.getDesciption().getPath());
			for(String str: ar){
				sb.append(str+"\n");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
