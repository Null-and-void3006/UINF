package app;

import java.awt.Component;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import fileHandling.FileSystemHandler;
import model.ColumnDescriptor;
import model.DataFile;
import model.MetaScheme;
import model.TableInfoStorage;
import model.ForeignTableBundle;
import model.Mode;
import model.Record;
import model.TableDescription;
import treeInfoModel.ColumnInfo;
import treeInfoModel.IRInfo;
import treeInfoModel.TableInfo;
import treeInfoModel.WorkspaceInfo;
import treeNodeModel.ColumnNode;
import treeNodeModel.IRNode;
import treeNodeModel.TableNode;
import treeNodeModel.WorkspaceNode;
import view.AboutDialog;
import view.ExceptionFrame;
import view.MainFrame;
import view.OpenMetaDialog;

public class AppCore {
	private WorkspaceNode wn=null;
	private static AppCore instance;
	private MainFrame mainFrame;
	private TableDescription currentTable;
	private ArrayList<TableInfoStorage> schemes;
	private MetaScheme metaScheme;
	private Mode state;
	private Record resultRecord;
	private IRNode resNode;
	private HashMap<String, String> whereClauses = new HashMap<String, String>();
	private HashMap<String, String> orderByClauses = new HashMap<String, String>();
	public TableDescription getCurrentTable() {
		return currentTable;
	}
	public MainFrame getMainFrame() {
		return mainFrame;
	}
	private Record currRecord;
	private WorkspaceNode workspace;
	private IRNode resource;
	final private static  String DEF="Sekvencijalne datoteke";
	final public static  boolean ONLINE=true;
	public static String getDef() {
		return DEF;
	}
	private int numberOfBlocks;
	public Record getCurrRecord() {
		return currRecord;
	}
	public void setCurrRecord(Record currRecord) {
		this.currRecord = currRecord;
	}
	public static AppCore getInstance(){
		if(instance==null){
			instance= new AppCore();
			instance.init();
		}
		return instance;
	}
	private void init(){
		state = Mode.BROWSE;
		workspace=new WorkspaceNode();/* insert workspace info here*/
		schemes=new ArrayList<>();
		
		//generateSchemesFromFolder(DEF);
		//tester();
		generateSchemesFromDatabase(getRowsByTableName( RepositoryMapper.getInstance().getTables()));
		
		metaScheme = new MetaScheme(schemes);
		//schemes.add(new MetaScheme(DEF));
		currentTable=schemes.get(0).getTableDescriptions().get(0);
		
		for(TableInfoStorage ms: schemes){
			ms.getTableDescriptions().get(0).getTable().setDescription(ms.getTableDescriptions().get(0));
			((IRNode)getTree().getChildAt(0)).addChild(ms.getTableDescriptions().get(0).getTable());
		}
		//((IRNode)getTree().getChildAt(0)).addChild(currentTable.getTable());

		
		//TODO: DO SOMETHING WITH irn
		
	
		//Ako neso neradi - verovatno je zbog ovoga
		((IRNode)(currentTable.getTable().getParent())).getInfo().setMeta(schemes.get(0));
		
		
		
		mainFrame= new MainFrame();
	}
	
	
	public ArrayList<ForeignTableBundle> getRowsByTableName(ArrayList<ForeignTableBundle> tables) {
		String query;
		for(ForeignTableBundle ftb: tables){
			ArrayList<String> rowArray = new ArrayList<>();
			query="SELECT * FROM "+ ftb.getIme();
			if(whereClauses.get(ftb.getIme())!=null){query+=" " + whereClauses.get(ftb.getIme());}
			if(orderByClauses.get(ftb.getIme())!=null){query+=" " + orderByClauses.get(ftb.getIme());}
			System.out.println(query);
			
			try {
				ResultSet rs = RepositoryCommander.getInstance().doQuery(query);

				while (rs.next()) {
					StringBuilder sb = new StringBuilder();
				    for(ColumnDescriptor cd:ftb.getKolone()){
				    	if(cd.getLength()>200)cd.setLength(200);
				    	
				    	
				    	sb.append( padString(rs.getString(cd.getName()),cd.getLength()) );
				    }
				    rowArray.add(sb.toString());
				}
				
				ftb.setRedovi(rowArray);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				ExceptionFrame frame = new ExceptionFrame(5,e);
				//e.printStackTrace();
			}
		}
		return tables;
	}
	private void generateSchemesFromFolder(String def2) {
		File folder = new File(def2);
		if(!folder.exists() || !folder.isDirectory()){
			
			return;
		}
		
		for(File fil: folder.listFiles()){
			if(fil.isFile() && (fil.getName().endsWith(".sek") || fil.getName().endsWith(".ind") || fil.getName().endsWith(".ser"))){
				schemes.add(new TableInfoStorage(fil.getAbsolutePath()));
				
			}
		}
		
	}
	
	private void generateSchemesFromDatabase(ArrayList<ForeignTableBundle> tabele){
		for(ForeignTableBundle ftb: tabele)
		schemes.add(new TableInfoStorage(ftb.getIme(),ftb.getKolone(),ftb.getRedovi()));
	}
	
	public TableInfoStorage getCurrMeta() {
		return ((IRNode)(currentTable.getTable().getParent())).getInfo().getMeta();
	}
	public MetaScheme getMetaScheme(){
		return metaScheme;
	}
	public void setMetaScheme(MetaScheme metaScheme){
		this.metaScheme = metaScheme;
	}
	public WorkspaceNode getTree(){
		if(wn!=null)return wn;
		
		WorkspaceInfo root=new WorkspaceInfo();
		root.setName("root");
		
		IRInfo res1=new IRInfo();
		res1.setName("Resource");
		
		IRNode ir1=new IRNode(res1);
		resNode = ir1;
		wn=new WorkspaceNode(root);
		//ir1.add(currentTable.getTable());
		wn.add(ir1);
		return wn;
	}
	public WorkspaceNode getDummy(){
		WorkspaceInfo root=new WorkspaceInfo();
		root.setName("root");
		
		IRInfo res1=new IRInfo();
		res1.setName("Resource 1");
		IRInfo res2=new IRInfo();
		res2.setName("Resource 2");
		IRInfo res3=new IRInfo();
		res3.setName("Resource 3");
		
		TableInfo t1=new TableInfo();
		t1.setName("Table 1");
		TableInfo t2=new TableInfo();
		t2.setName("Table 2");
		TableInfo t3=new TableInfo();
		t3.setName("Table 3");
		
		ColumnInfo c1=new ColumnInfo();
		c1.setName("Column 1");
		ColumnInfo c2=new ColumnInfo();
		c2.setName("Column 2");
		ColumnInfo c3=new ColumnInfo();
		c3.setName("Column 3");
		
		WorkspaceNode wn=new WorkspaceNode(root);
		IRNode ir1=new IRNode(res1);
		IRNode ir2=new IRNode(res2);
		IRNode ir3=new IRNode(res3);
		TableNode tn1=new TableNode(t1);
		TableNode tn2=new TableNode(t2);
		TableNode tn3=new TableNode(t3);
		ColumnNode cn1=new ColumnNode(c1);
		ColumnNode cn2=new ColumnNode(c2);
		ColumnNode cn3=new ColumnNode(c3);
		
		wn.add(ir1);
		wn.add(ir2);
		wn.add(ir3);
		ir1.add(tn1);
		ir1.add(tn2);
		ir1.add(tn3);
		tn1.add(cn1);
		tn1.add(cn2);
		tn1.add(cn3);
		
		return wn;
		
	}
	public WorkspaceNode getWorkspaceNode(){
		return workspace;
	}
	public IRNode getResource() {
		return resource;
	}
	
	public void seeAbout(){
		new AboutDialog();
	}
	public int getNumberOfBlocks() {
		return numberOfBlocks;
	}
	public void setNumberOfBlocks(int numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
	}
	public ArrayList<TableInfoStorage> getSchemes() {
		return schemes;
	}
	public void openMeta() {
	 //MetaScheme ms=	new MetaScheme(((OpenMetaDialog)DialogHandler.getInstance().getCurrentDialog()).getPath());
	
		
	getCurrMeta().makeTable(((OpenMetaDialog)DialogHandler.getInstance().getCurrentDialog()).getPath());
	
	getCurrMeta().
	getLatest().
	getTable().
	getInfo().
	setName(FileSystemHandler.parsePathAndName(((OpenMetaDialog)DialogHandler.getInstance().getCurrentDialog()).getPath())[1]);
	// ms.getTable().getInfo().setName(FileSystemHandler.parsePathAndName(((OpenMetaDialog)DialogHandler.getInstance().getCurrentDialog()).getPath())[1]);
	((IRNode)(currentTable.getTable().getParent())).addChild(getCurrMeta().getLatest().getTable());
	 currentTable=getCurrMeta().getLatest();
	
	 
		DialogHandler.getInstance().closeCurrentDialog();
		SwingUtilities.updateComponentTreeUI(mainFrame.getLeft().getJTree());
	}
	public void setCurrentTable(TableDescription currentTable) {
		this.currentTable = currentTable;
	}
	public Mode getState() {
		return state;
	}
	public void setState(Mode state) {
		this.state = state;
		getInstance().getMainFrame().updateStateful();
		/*for(Component component : getInstance().getMainFrame().getComponents()){
			component.repaint();
		}*/
	}
	
	public ArrayList<TableInfo> getAllTableInfos(){
		ArrayList<TableInfo> table = new ArrayList<>();
		IRNode node = (IRNode)AppCore.getInstance().getTree().getChildAt(0);
			for(int i = 0; i<node.getChildCount();i++){
				table.add(((TableNode)(node.getChildAt(i))).getInfo());
			}
		return table;
	}
	public Record foundRecord(){
		return resultRecord;
	}
	public void setRecord(Record record){
		resultRecord = record;
	}
	
	public ArrayList<TableNode> getAllTableNodes(){
		ArrayList<TableNode> table = new ArrayList<>();
		IRNode node = (IRNode)AppCore.getInstance().getTree().getChildAt(0);
			for(int i = 0; i<node.getChildCount();i++){
				table.add(((TableNode)(node.getChildAt(i))));
			}
		return table;
	}
	
	public String padString(String start, int length){
		StringBuilder sb = new StringBuilder(start);
		if(length - start.length() < 0)return start.substring(0, length);
		for(int i=0;i<length - start.length();i++)
		{
			sb.append(' ');
		}
		
		return sb.toString();
	}
	
	public void hardReset(){
		
		String old = AppCore.getInstance().getMainFrame().getPanelRight().getTbn().getInfo().getName();
		
		wn = null;
		workspace=new WorkspaceNode();
		schemes=new ArrayList<>();
		generateSchemesFromDatabase(getRowsByTableName( RepositoryMapper.getInstance().getTables()));
		metaScheme = new MetaScheme(schemes);
		currentTable=schemes.get(0).getTableDescriptions().get(0);
		
		for(TableInfoStorage ms: schemes){
			ms.getTableDescriptions().get(0).getTable().setDescription(ms.getTableDescriptions().get(0));
			((IRNode)getTree().getChildAt(0)).addChild(ms.getTableDescriptions().get(0).getTable());
		}

		((IRNode)(currentTable.getTable().getParent())).getInfo().setMeta(schemes.get(0));
		
		
		
		mainFrame.constructorReset();
		TreeNode node = null;
		for(int i = 0; i < resNode.getChildCount(); i++)
		{
			if(resNode.getChildAt(i).toString() == old){
				node = resNode.getChildAt(i);
			}
			
		}
		
		if(node == null) return;
		TreePath tp = new TreePath(((DefaultMutableTreeNode)node).getPath());
		System.out.println(tp);
		mainFrame.getLeft().getJTree().expandPath(tp);
		mainFrame.getLeft().getJTree().setSelectionPath(tp);
		if(AppCore.getInstance().getMainFrame().getLeft().getJTree().getSelectionPath().getLastPathComponent() instanceof TableNode){
			AppCore.getInstance().getMainFrame().getPanelRight().replaceTable((TableNode) AppCore.getInstance().getMainFrame().getLeft().getJTree().getSelectionPath().getLastPathComponent());
			AppCore.getInstance().setCurrentTable(((TableNode)AppCore.getInstance().getMainFrame().getLeft().getJTree().getSelectionPath().getLastPathComponent()).getDesciption());
			//throw new ExpandVetoException(arg0);
		}
	}
	
	public void putWhere(String table, String clause){
		whereClauses.put(table, clause);
		hardReset();
	}
	
	public void putOrderBy(String table, String clause){
		orderByClauses.put(table, clause);
		hardReset();
	}
}
