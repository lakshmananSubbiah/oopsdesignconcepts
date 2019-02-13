package oopsconcepts.dbhelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface DatabaseHelper {
	
	public ResultSet insertData(String tableName,Map<String,Object> values) throws SQLException;
	
	public ResultSet getAll(String TABLENAME, Map<String,Object> conditions, int from, int limit)throws SQLException;
	
	public ResultSet getRow(String tableName, String columnName, Object value) throws SQLException;
	
	public boolean deleteData(String query);
	
	public ResultSet updateData(String query);
	
}
