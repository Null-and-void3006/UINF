package model;

import java.io.IOException;
import java.util.ArrayList;

public interface FileOps {
	public boolean fetchBlock(int start, int end);
	public boolean addRecord(Record record);
	public boolean updateRecord(Record record, Record record2);
	public Record findRecord(Record searchRec,int position);
	public boolean deleteRecord(Record searchRec);
	public void sortMDI();
	public void sortMM();
}
