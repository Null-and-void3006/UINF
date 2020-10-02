package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ExceptionFrame extends JDialog{
	
	private int exceptionType;
	private JPanel panel;
	private JLabel exceptionText;
	private SQLException exception;
	
	public ExceptionFrame(int exceptionType,SQLException exception) throws HeadlessException {
		super();
		this.exceptionType = exceptionType;
		this.exception = exception;
		
		if (exceptionType == 0) {
			if(exception.getMessage() != "The statement did not return a result set.")
			JOptionPane.showMessageDialog(this, exception.getMessage(), "Add record error", JOptionPane.ERROR_MESSAGE);
			
		}else if (exceptionType == 1) {
			if(exception.getMessage() != "The statement did not return a result set.")
			JOptionPane.showMessageDialog(this, exception.getMessage(), "Remove record error", JOptionPane.ERROR_MESSAGE);
			
		}else if (exceptionType == 2) {
			if(exception.getMessage() != "The statement did not return a result set.")
			JOptionPane.showMessageDialog(this, exception.getMessage(), "Update record error", JOptionPane.ERROR_MESSAGE);
			
		}else if (exceptionType == 3) {
			JOptionPane.showMessageDialog(this, exception.getMessage(), "Calculate count error", JOptionPane.ERROR_MESSAGE);
			
		}else if (exceptionType == 4) {
			JOptionPane.showMessageDialog(this, exception.getMessage(), "Calculate average error", JOptionPane.ERROR_MESSAGE);
			
		}else if (exceptionType == 5) {
			JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}

	}
		
		
		
	

}
