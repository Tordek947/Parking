package ua.hpopov.parking.datasource.dao.sql;

public class SqlSchemaStructureManager {
	private static SqlSchemaStructureManager instance=null;
	private SqlSchemaStructureManager() {
		
	}
	
	public static SqlSchemaStructureManager getInstance() {
		if (instance == null) {
			instance = new SqlSchemaStructureManager();
		}
		return instance;
	}
	
	
}
