package app;

import java.util.Stack;

import javax.swing.JDialog;

public class DialogHandler {
	
	
	private static DialogHandler instance=null;
	private Stack<JDialog> dialogStack= new Stack<>();
	
	
	public static DialogHandler getInstance(){
		if(instance==null){
			instance = new DialogHandler();
		}
		return instance;
	}
	
	private void reorganizeDialogStack(){
		if(!dialogStack.isEmpty())
		while(dialogStack.peek()==null)dialogStack.pop();
	}
	
	public void pushDialog(JDialog jj){
		reorganizeDialogStack();
		dialogStack.push(jj);
	}
	
	public JDialog popDialog(){
		reorganizeDialogStack();
		return dialogStack.pop();
	}
	
	public JDialog getCurrentDialog(){
		reorganizeDialogStack();
		return dialogStack.peek();
	}
	
	public void closeCurrentDialog(){
		reorganizeDialogStack();
		popDialog().dispose();
	}
	
	
}
