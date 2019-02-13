package oopsconcepts.dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DatabaseImpl implements DatabaseHelper {

	private static DatabaseImpl dbImpl;
	
	private Connection connection = null;
	
	private DatabaseImpl() {
		try {
			Class.forName("org.postgresql.Driver");
			connection =  DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/mydb", "lakshmanan-zt71",
					"demo1234");
		}
		catch(ClassNotFoundException e ) {
			throw new RuntimeException(e.getMessage());
		}
		catch(SQLException sqlExcep) {
			throw new RuntimeException(sqlExcep.getMessage());
		}
	}
	
	public static DatabaseImpl getInstance() {
		if(dbImpl == null) {
			dbImpl = new DatabaseImpl();
		}
		return dbImpl;
	}

	public ResultSet insertData(String tableName,Map<String,Object> values) throws SQLException {
		StringBuilder sql = new StringBuilder( "INSERT INTO "+tableName+" (");
		for(String key: values.keySet()) {
			sql.append(key+",");
		}
		sql.replace(sql.length()-1, sql.length(), ")");
		sql.append(" VALUES (");
		for (Map.Entry<String, Object> data : values.entrySet() ) {
			Object obj = data.getValue();
			if(obj instanceof String) {
				sql.append(" '"+obj+" ',");
			}
			else {
				sql.append(" "+obj+",");
			}
		}
		sql.replace(sql.length()-1, sql.length(), ");");
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 ps = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
		 ps.executeUpdate();
		 rs = ps.getGeneratedKeys();
		 return rs;
	}

	public ResultSet getAll(String TABLENAME, Map<String,Object> conditions, int from, int limit)throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT * FROM "+TABLENAME+" ");
		if(conditions != null) {
			
		}
		limit = (limit == 0)?10:limit;
		sql.append("LIMIT "+limit+ " OFFSET "+from);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql.toString());
		return resultSet;
	}

	public boolean deleteData(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	public ResultSet updateData(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ResultSet getRow(String tableName, String columnName, Object value) throws SQLException {
		StringBuilder sql = new StringBuilder();
		if(value instanceof String)
			 sql = new StringBuilder( "SELECT * FROM "+tableName+" WHERE "+columnName+" = '"+value+"'");
		else
			 sql = new StringBuilder( "SELECT * FROM "+tableName+" WHERE "+columnName+" = "+value);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql.toString());
		return resultSet;
	}

}
