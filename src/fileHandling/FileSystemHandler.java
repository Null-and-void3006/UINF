package fileHandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Stack;

import app.AppCore;

public class FileSystemHandler {

	
	public static void deleteFileSystem(File f){
		if(!f.exists())return;
		Stack<File> st=new Stack<>();
		st.push(f);
		File tmp;
		while(!st.empty()){
			tmp=st.peek();
			if(tmp.isFile()){tmp.delete();st.pop();}
			else{
				if(tmp.isDirectory()){
					if(tmp.listFiles().length==0){tmp.delete();st.pop();}
					else{
						for(File i:tmp.listFiles())st.push(i);
					}
				}
			}
		}
	}
	
	public static String trimLastDirectory(String s){
		return s.substring(0,s.lastIndexOf("\\"));
	}
	
	public static String[] parsePathAndName(String s){
		String[] a=new String[2];
		a[0]=FileSystemHandler.trimLastDirectory(s);
		a[1]=new File(s).getName();
		
		
		return a;
	}
	public static void saveStringArray(ArrayList<String> lst, String s){
		if(lst==null) { new File(s).mkdir(); return; }
		try {
			PrintWriter pr=new PrintWriter(new File(s));
			for(String i: lst){
				pr.write(i+"\n");
			}
			pr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> loadStringArray(String file){
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String s;
			ArrayList<String> lst=new ArrayList<>();
			while((s=br.readLine())!=null)lst.add(s);
			br.close();
			return lst;
		} catch (FileNotFoundException e) {
			try {
				System.out.println(file+"<--");
				PrintWriter pr=new PrintWriter(new File(file));
				pr.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return new ArrayList<>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean validFileName(String s){
		if(s.contains("*"))return false;
		if(s.contains("<"))return false;
		if(s.contains(">"))return false;
		if(s.contains("|"))return false;
		if(s.contains(":"))return false;
		if(s.contains("/"))return false;
		if(s.contains("\\"))return false;
		if(s.contains("\""))return false;
		return true;
	}

	public static void copyContents(File source, File destination) {
			try{
			
			FileChannel src = new FileInputStream(source).getChannel();
			FileChannel dest = new FileOutputStream(destination).getChannel();
			
			dest.transferFrom(src, 0, src.size());
			src.close();
			dest.close();
			}
			catch(Exception e1){e1.printStackTrace();}
		
	}
	
	public static void removeLine(File target, String line){
		try{
			File inputFile = target;
			File tempFile = new File("123.tmp.donttouch");
			tempFile.delete();
			tempFile.createNewFile();
	
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String lineToRemove = line;
			String currentLine;
	
			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    if(currentLine.equals(lineToRemove)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			inputFile.delete();
			tempFile.renameTo(inputFile);
		}
		catch(Exception z){z.printStackTrace();}
		
	}
	
	public static void appendLine(File target, String line){

	        PrintWriter pw;
			try {
				pw = new PrintWriter(new FileOutputStream( target,true));
				pw.write("\n"+line);
		        pw.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	}
}
