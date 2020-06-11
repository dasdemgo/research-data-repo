package at.ac.tuwien.persistence;

import java.sql.ResultSet;

public interface DataStoreDao {

	public void execute(String query);

	public ResultSet executeQuery(String query);
}
