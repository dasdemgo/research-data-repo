package at.ac.tuwien.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.persistence.DataStoreDao;

@Repository
public class DataStoreDaoImpl implements DataStoreDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataStoreDaoImpl.class);

//	@Autowired
//	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void execute(String query) {
		Statement stmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			stmt.execute(query);
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String query) {
		Statement stmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	private Connection getConnection() {
		Connection connection = null;
		Session session = sessionFactory.openSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		connection = sessionImpl.connection();
		return connection;

	}

}
