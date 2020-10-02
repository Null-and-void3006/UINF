package app;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryCommander {
	private DatabaseConnector databaseConnector;
	private static RepositoryCommander instance;
	
	public RepositoryCommander() {
		this.databaseConnector = new DatabaseConnector();
	}
	
	public static RepositoryCommander getInstance(){
		if(instance!=null) return instance;
		instance = new RepositoryCommander();
		return instance;
	}
	
	public ResultSet doQuery(String query) throws SQLException{
		
		ResultSet rs = databaseConnector.executeStatement(query);
		
		return rs;
		
	}

}
