package controller;

import treeNodeModel.WorkspaceNode;

public interface Observer {
	
	public void syncState(Observed subject);
}
