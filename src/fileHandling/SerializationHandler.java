package fileHandling;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import app.AppCore;



public class SerializationHandler {

public static ObjectOutputStream getObjectOutputStream(String s){
	
		
		try {
			return new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(s))));
			
		} catch (IOException e) {
			System.out.println("IOException while accessing "+s);
			return null;
		}
	}
	
public static ObjectInputStream getObjectInputStream(String s){
	
		
		try {
			return new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(s))));
			
		} catch (IOException e) {
			System.out.println("IOException while accessing "+s);
			return null;
		}
	}


public static <T extends Object> T readObject(String file, Class<T> type){
	
	//Standard Read
	ObjectInputStream ois=getObjectInputStream(file);
	if(ois==null)return null;
	T result=null;
	try {
		result= type.cast( ois.readObject());
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		try {
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	return result;
	}


public static void writeObject(String file,Object s){
	
		//Standard Write
	ObjectOutputStream ous=getObjectOutputStream(file);
	
	try {
		ous.writeObject(s);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		try {
			ous.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
}
