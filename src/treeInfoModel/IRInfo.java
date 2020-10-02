package treeInfoModel;

import java.io.Serializable;

import model.TableInfoStorage;

public class IRInfo implements Serializable{
	private String name;
	private TableInfoStorage meta;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.meta=new TableInfoStorage();
	}

	public TableInfoStorage getMeta() {
		return meta;
	}

	public void setMeta(TableInfoStorage meta) {
		this.meta = meta;
	}
	
	
}
