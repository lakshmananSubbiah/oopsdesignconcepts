package oopsconcepts.principles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oopsconcepts.dbhelper.DatabaseHelper;
import oopsconcepts.dbhelper.DatabaseImpl;

public class PrincipleAPI {

	private static PrincipleAPI instance;
	
	private static final String PRINCIPLE_TABLE_NAME = "PRINCIPLES";
	
	private DatabaseHelper dbHelper;
	
	private PrincipleAPI() {
		
	}
	
	public static PrincipleAPI getInstance(DatabaseHelper dbHelper) {
		if(instance==null) {
			instance = new PrincipleAPI();
		}
		instance.dbHelper = dbHelper;
		return instance;
	}
	
	public List<Principle> getAllPrinciples(){
		return null;
	}
	
	public Principle createPrinciple(Principle principle) throws SQLException {
		Map<String,Object> principlesMap = convertPrincipleToMap(principle);
		ResultSet rs = dbHelper.insertData(PRINCIPLE_TABLE_NAME, principlesMap);
		if(rs.next()) {
			return new Principle(rs.getLong(1), rs.getString(2).trim(), rs.getString(3));
		}
		throw new RuntimeException("the data is not present");
	}
	
	private Map<String,Object> convertPrincipleToMap(Principle principle){
		Map<String,Object> principleMap = new HashMap<String,Object>();
		principleMap.put("PRINCIPAL_NAME", principle.getPrincipleName());
		principleMap.put("DEFINITION", principle.getDefinition());
		return principleMap;
	}
	
	
	public static void main(String[] args) throws SQLException {
		Principle principle = new Principle.Builder("SINGLE_RESPONSIBILITY_PRINCIPLE", "").build();
		DatabaseHelper dbHelper = DatabaseImpl.getInstance();
//		System.out.println(PrincipleAPI.getInstance(dbHelper).createPrinciple(principle));
	//	System.out.println(PrincipleAPI.getInstance(dbHelper).get(2L));
		System.out.println(PrincipleAPI.getInstance(dbHelper).get(0, 10));
	}
	
	private Principle get(Long principleId) throws SQLException {
		ResultSet rs = dbHelper.getRow(PRINCIPLE_TABLE_NAME, "PRINCIPLE_ID", principleId);
		if(rs.next()) {
			return new Principle(rs.getLong(1), rs.getString(2).trim(), rs.getString(3));
		}
		throw new RuntimeException("the data is not present");
	}
	
	private List<Principle> get(int from, int limit) throws SQLException {
		List<Principle> list = new ArrayList<Principle>();
		ResultSet rs = dbHelper.getAll(PRINCIPLE_TABLE_NAME,null,from,limit);
		while(rs.next()) {
			list.add(new Principle(rs.getLong(1), rs.getString(2).trim(), rs.getString(3)));
		}
		return list;
	}
	
}
