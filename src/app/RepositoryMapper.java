package app;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ColumnDescriptor;
import model.ForeignTableBundle;

public class RepositoryMapper {
	private static RepositoryMapper instance=null;
	private ResultSet result;
	private String[][] databaseInfo;
	private DatabaseMetaData meta;
	private ArrayList<ForeignTableBundle> tables;
	private DatabaseConnector databaseConnector;
	
	public RepositoryMapper() {
		// TODO Auto-generated constructor stub
		databaseConnector = new DatabaseConnector();
		try {
			meta=databaseConnector.getConnection().getMetaData();
			databaseInfo = new String[100][7];
			tables = getTableInfo();
			
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
	}
	
	public static RepositoryMapper getInstance(){
		if(instance!=null) return instance;
		instance = new RepositoryMapper();
		return instance;
	}
	
	
	public ArrayList<ForeignTableBundle> getTables() {
		return tables;
	}

	public void setTables(ArrayList<ForeignTableBundle> tables) {
		this.tables = tables;
	}

	private ArrayList<ForeignTableBundle> getTableInfo() throws SQLException{
		ResultSet pk;
		result = meta.getColumns(null,null,"Klijent",null);
		pk = meta.getPrimaryKeys(null, null, "Klijent");
		pk.next();
		ArrayList<ColumnDescriptor> columns= new ArrayList<>();
		ColumnDescriptor currentDescriptor = new ColumnDescriptor();
		ArrayList<ForeignTableBundle> bundles = new ArrayList<>();
		String tableName = "Klijent";
		while (result.next()) {
			currentDescriptor.setName(result.getString("COLUMN_NAME"));
			currentDescriptor.setType(result.getString("DATA_TYPE"));
			currentDescriptor.setLength(Integer.parseInt(result.getString("COLUMN_SIZE")));
			currentDescriptor.setNullable(Boolean.getBoolean(result.getString("IS_NULLABLE")));
			//if (result.getString("PKCOLUMN_NAME").equals(currentDescriptor.getName())) currentDescriptor.setPk(true);
			//else currentDescriptor.setPk(false);;
			if(pk.getString("COLUMN_NAME").equals(currentDescriptor.getName())) currentDescriptor.setPk(true);
			else currentDescriptor.setPk(false);
			columns.add(currentDescriptor);
			currentDescriptor= new ColumnDescriptor();
		}
		
		bundles.add(new ForeignTableBundle(tableName, columns));
		columns= new ArrayList<>();
		pk = meta.getPrimaryKeys(null, null, "Narudcbine");
		pk.next();
		result = meta.getColumns(null,null,"Narudcbine",null);
		tableName = "Narudcbine";
		while (result.next()) {
			currentDescriptor.setName(result.getString("COLUMN_NAME"));
			currentDescriptor.setType(result.getString("DATA_TYPE"));
			currentDescriptor.setLength(Integer.parseInt(result.getString("COLUMN_SIZE")));
			currentDescriptor.setNullable(Boolean.getBoolean(result.getString("IS_NULLABLE")));
			//if (result.getString("PKCOLUMN_NAME").equals(currentDescriptor.getName())) currentDescriptor.setPk(true);
			//else currentDescriptor.setPk(false);;
			if(pk.getString("COLUMN_NAME").equals(currentDescriptor.getName())) currentDescriptor.setPk(true);
			else currentDescriptor.setPk(false);
			columns.add(currentDescriptor);
			currentDescriptor= new ColumnDescriptor();
		}
		bundles.add(new ForeignTableBundle(tableName, columns));
		columns= new ArrayList<>();
		pk = meta.getPrimaryKeys(null, null, "Odredicte");
		pk.next();
		result = meta.getColumns(null,null,"Odredicte",null);
		tableName = "Odredicte";
		while (result.next()) {
			currentDescriptor.setName(result.getString("COLUMN_NAME"));
			currentDescriptor.setType(result.getString("DATA_TYPE"));
			currentDescriptor.setLength(Integer.parseInt(result.getString("COLUMN_SIZE")));
			currentDescriptor.setNullable(Boolean.getBoolean(result.getString("IS_NULLABLE")));
			if(pk.getString("COLUMN_NAME").equals(currentDescriptor.getName())) currentDescriptor.setPk(true);
			else currentDescriptor.setPk(false);
			columns.add(currentDescriptor);
			currentDescriptor= new ColumnDescriptor();
		}
		bundles.add(new ForeignTableBundle(tableName, columns));
		columns= new ArrayList<>();
		pk = meta.getPrimaryKeys(null, null, "Proizvod");
		pk.next();
		result = meta.getColumns(null,null,"Proizvod",null);
		tableName = "Proizvod";
		while (result.next()) {
			currentDescriptor.setName(result.getString("COLUMN_NAME"));
			currentDescriptor.setType(result.getString("DATA_TYPE"));
			currentDescriptor.setLength(Integer.parseInt(result.getString("COLUMN_SIZE")));
			currentDescriptor.setNullable(Boolean.getBoolean(result.getString("IS_NULLABLE")));
			if(pk.getString("COLUMN_NAME").equals(currentDescriptor.getName())) currentDescriptor.setPk(true);
			else currentDescriptor.setPk(false);
			columns.add(currentDescriptor);
			currentDescriptor= new ColumnDescriptor();
		}
		bundles.add(new ForeignTableBundle(tableName, columns));
		columns= new ArrayList<>();
		pk = meta.getPrimaryKeys(null, null, "Skladicte");
		pk.next();
		result = meta.getColumns(null,null,"Skladicte",null);
		tableName = "Skladicte";
		while (result.next()) {
			currentDescriptor.setName(result.getString("COLUMN_NAME"));
			currentDescriptor.setType(result.getString("DATA_TYPE"));
			currentDescriptor.setLength(Integer.parseInt(result.getString("COLUMN_SIZE")));
			currentDescriptor.setNullable(Boolean.getBoolean(result.getString("IS_NULLABLE")));
			if(pk.getString("COLUMN_NAME").equals(currentDescriptor.getName())) currentDescriptor.setPk(true);
			else currentDescriptor.setPk(false);
			columns.add(currentDescriptor);
			currentDescriptor= new ColumnDescriptor();
		}
		bundles.add(new ForeignTableBundle(tableName, columns));
		columns= new ArrayList<>();
		pk = meta.getPrimaryKeys(null, null, "Vozac");
		pk.next();
		result = meta.getColumns(null,null,"Vozac",null);
		tableName = "Vozac";
		while (result.next()) {
			currentDescriptor.setName(result.getString("COLUMN_NAME"));
			currentDescriptor.setType(result.getString("DATA_TYPE"));
			currentDescriptor.setLength(Integer.parseInt(result.getString("COLUMN_SIZE")));
			currentDescriptor.setNullable(Boolean.getBoolean(result.getString("IS_NULLABLE")));
			if(pk.getString("COLUMN_NAME").equals(currentDescriptor.getName())) currentDescriptor.setPk(true);
			else currentDescriptor.setPk(false);
			columns.add(currentDescriptor);
			currentDescriptor= new ColumnDescriptor();
		}
		bundles.add(new ForeignTableBundle(tableName, columns));
		columns= new ArrayList<>();
		pk = meta.getPrimaryKeys(null, null, "Vozilo");
		pk.next();
		result = meta.getColumns(null,null,"Vozilo",null);
		tableName = "Vozilo";
		while (result.next()) {
			currentDescriptor.setName(result.getString("COLUMN_NAME"));
			currentDescriptor.setType(result.getString("DATA_TYPE"));
			currentDescriptor.setLength(Integer.parseInt(result.getString("COLUMN_SIZE")));
			currentDescriptor.setNullable(Boolean.getBoolean(result.getString("IS_NULLABLE")));
			if(pk.getString("COLUMN_NAME").equals(currentDescriptor.getName())) currentDescriptor.setPk(true);
			else currentDescriptor.setPk(false);
			columns.add(currentDescriptor);
			currentDescriptor= new ColumnDescriptor();
		}
		bundles.add(new ForeignTableBundle(tableName, columns));
		columns= new ArrayList<>();
		pk = meta.getPrimaryKeys(null, null, "Zaposleni");
		pk.next();
		result = meta.getColumns(null,null,"Zaposleni",null);
		tableName = "Zaposleni";
		while (result.next()) {
			currentDescriptor.setName(result.getString("COLUMN_NAME"));
			currentDescriptor.setType(result.getString("DATA_TYPE"));
			currentDescriptor.setLength(Integer.parseInt(result.getString("COLUMN_SIZE")));
			currentDescriptor.setNullable(Boolean.getBoolean(result.getString("IS_NULLABLE")));
			if(pk.getString("COLUMN_NAME").equals(currentDescriptor.getName())) currentDescriptor.setPk(true);
			else currentDescriptor.setPk(false);
			columns.add(currentDescriptor);
			currentDescriptor= new ColumnDescriptor();
		}
		bundles.add(new ForeignTableBundle(tableName, columns));
		return bundles;
		}

}
