package app;
import java.sql.*;

public class DatabaseConnector {
	
	private String username;
	private String password;
	private String databaseName;
	private String url;
	private Connection connection;
	
	public DatabaseConnector() {
		username = "tim_205_4_ui2018";
		password = "9L8x!!Bb6H22=W_K";
		databaseName = "tim_205_4_ui2018";
		url = "147.91.175.155";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection( "jdbc:sqlserver://"+url+";databaseName="+databaseName,username,password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet executeStatement(String query) throws SQLException{
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		return resultSet;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	

}
