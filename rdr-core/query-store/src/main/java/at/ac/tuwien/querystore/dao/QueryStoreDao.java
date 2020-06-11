package at.ac.tuwien.querystore.dao;

import java.util.Optional;

import at.ac.tuwien.querystore.model.Query;

public interface QueryStoreDao {
	public void persistQuery(Query query);

	public Optional<Query> getQueryForResultSetHash(String resultSetHash);
}
