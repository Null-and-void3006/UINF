package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JTree;


import fileHandling.FileSystemHandler;
import fileHandling.SerializationHandler;
import treeInfoModel.ColumnInfo;
import treeInfoModel.TableInfo;
import treeNodeModel.ColumnNode;
import treeNodeModel.TableNode;
import view.JTreeDialog;

public class TableInfoStorage implements Serializable{
	ArrayList<TableDescription> tableDescriptions=new ArrayList<>();
	DataFile file=null;
	private String origin;
	public TableInfoStorage(){}
	
	public ArrayList<TableDescription> getTableDescriptions() {
		return tableDescriptions;
	}
	public TableInfoStorage(String s){
		origin = s;
		makeTable(s);
	}
	
	public TableInfoStorage(String ime,ArrayList<ColumnDescriptor> kolone, ArrayList<String> redovi) {
		makeForeignTable(ime, kolone, redovi);
	}
	
	private void makeForeignTable(String ime, ArrayList<ColumnDescriptor> kolone, ArrayList<String> redovi) {
		 String content="";
		 
		 String tree="";
		 TableNode table = new TableNode();
		 int numFields=0;
		 //lista = kolone
		 String[] niz;
		 for(ColumnDescriptor col: kolone) {

			 
			 numFields++;
			 ColumnNode cn=new ColumnNode();
			 ColumnInfo ci=new ColumnInfo();
			 cn.setInfo(ci);
			 ci.setName(col.getName());
			 ci.setField(new Field(col.getName(), col.getType(), col.getLength(), col.isPk(), false,false));
			 table.addChild(cn);
		 }
		 
		 initForeignTable(table, ime, redovi);//Content ocekivano da je datoteka - popravi
		 file=new SequenceFile(table);
		 tableDescriptions.add(new TableDescription(content, "", file, tree, table, numFields));
	}

	public String getOrigin() {
		return origin;
	}

	public void makeTable(String s) {
		 String content="";
		 String path="";
		 
		 String tree="";
		 TableNode table;
		 int numFields=0;
		
		ArrayList<String> lista=FileSystemHandler.loadStringArray(s);
		System.out.println(lista.size()+" in "+s);
		table= new TableNode(); 
		String[] niz;
		path=s;
		for(String cl: lista){
			niz=cl.split("/");
			
			if(niz[0].compareTo("path")==0){ content=FileSystemHandler.parsePathAndName(s)[0]+"/"+ niz[1]; }
			else if(niz[0].compareTo("field")==0){
				numFields++;
				ColumnNode cn=new ColumnNode();
				ColumnInfo ci=new ColumnInfo();
				cn.setInfo(ci);
				ci.setName(niz[1]);
				ci.setField(new Field(niz[1], niz[2], Integer.parseInt(niz[3]), niz[4].compareTo("true")==0?true:false, false,false));
				
				table.addChild(cn);
				
				}
			else if(niz[0].compareTo("tree")==0){
				tree=content=FileSystemHandler.parsePathAndName(s)[0]+"\\"+ niz[1];
			}
			else if(niz[0].compareTo("overZone")==0){/*TODO: Nem blage*/}
			
		}
		
		
		
		initTable(table,content);
		
		
		
		
		if(path.substring(path.lastIndexOf(".")).equals(".sek")) file=new SequenceFile(table);
		if(path.substring(path.lastIndexOf(".")).equals(".ind")) file=new IndexFile(table);
		if(path.substring(path.lastIndexOf(".")).equals(".ser")) file=new SerialFile(table);
		
		if(tree!=""){
			new JTreeDialog(SerializationHandler.readObject(tree, JTree.class));
			}
		
		tableDescriptions.add(new TableDescription(content, path, file, tree, table, numFields));
	}
	
	
	
	public void initTable( TableNode table ,String content){
			
		ArrayList<String> niz=FileSystemHandler.loadStringArray(content);
		table.getInfo().setName(content);
		String[][] informacije = new String[niz.size()][table.getColumns().size()];
		String pom;
		for(int i=0;i<niz.size();i++){//Prolazi kroz sve linije input fajla
			pom=niz.get(i);
			int br=0;
			//System.out.println(pom);
			for(int j=0; j<table.getColumns().size(); j++){//Prolazi kroz svaku kolonu tabele
				informacije[i][j]="";
				
				for(int k=0;k<table.getColumns().get(j).getInfo().getField().getLength() && br < pom.length();k++){//Prolazi kroz input string karakter po karakter
					informacije[i][j]+=pom.charAt(br);
					
					//System.out.println(pom.substring(br, br+1)+" <-- ovo");
					br++;
					
				}
			}
			
		}
		
		for(String[] cl: informacije){
			Record rc=table.getInfo().addRecord();
			for(String cl2:cl){ 
				rc.addValue(cl2);
			}
			/**/
			String lul="";
			for(int i=0;i<table.getColumns().size()-1;i++){
				if(table.getColumns().get(i).getInfo().getField().isPK()) {lul+=rc.getValues().get(i);}
				
			}
			rc.setKey(lul);
			
		}
		
	}
	
	public void initForeignTable( TableNode table ,String name, ArrayList<String> niz){
		System.out.println(table+" "+name+" "+niz);
		table.getInfo().setName(name);
		String[][] informacije = new String[niz.size()][table.getColumns().size()];
		String pom;
		for(int i=0;i<niz.size();i++){//Prolazi kroz sve linije input fajla
			pom=niz.get(i);
			int br=0;
			//System.out.println(pom);
			for(int j=0; j<table.getColumns().size(); j++){//Prolazi kroz svaku kolonu tabele
				informacije[i][j]="";
				
				for(int k=0;k<table.getColumns().get(j).getInfo().getField().getLength() && br < pom.length();k++){//Prolazi kroz input string karakter po karakter
					informacije[i][j]+=pom.charAt(br);
					
					//System.out.println(pom.substring(br, br+1)+" <-- ovo");
					br++;
					
				}
			}
			
		}
		
		for(String[] cl: informacije){
			Record rc=table.getInfo().addRecord();
			for(String cl2:cl){ 
				rc.addValue(cl2);
			}
			/**/
			String lul="";
			for(int i=0;i<table.getColumns().size()-1;i++){
				if(table.getColumns().get(i).getInfo().getField().isPK()) {lul+=rc.getValues().get(i);}
				
			}
			rc.setKey(lul);
			
		}
		
	}
	
	public TableDescription getLatest(){
		return tableDescriptions.get(tableDescriptions.size()-1);
	}
	public DataFile getFile() {
		// TODO Auto-generated method stub
		return file;
	}
	public TableNode getTable() {
		// TODO Auto-generated method stub
		return null;
	}
	public char[] getNumFields() {
		System.out.println("getNumberFields - MetaScheme");
		return null;
	}
}
