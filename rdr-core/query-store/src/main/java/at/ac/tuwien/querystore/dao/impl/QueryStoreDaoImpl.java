package at.ac.tuwien.querystore.dao.impl;

import java.util.Optional;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.querystore.dao.QueryStoreDao;
import at.ac.tuwien.querystore.model.Query;

@Repository
public class QueryStoreDaoImpl implements QueryStoreDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	private static final Logger LOGGER = LoggerFactory.getLogger(QueryStoreDaoImpl.class);

	@Override
	public void persistQuery(Query query) {
		this.session = sessionFactory.openSession();
		this.session.beginTransaction();
		this.session.save(query);
		this.session.getTransaction().commit();
		this.session.close();
	}

	@Override
	public Optional<Query> getQueryForResultSetHash(String resultSetHash) {
		this.session = sessionFactory.openSession();
		this.session.beginTransaction();
		org.hibernate.query.Query query = this.session
				.createQuery("from query_store where resultset_hash = :resultSetHash");
		query.setParameter("resultSetHash", resultSetHash);

		Optional<Query> resultQuery = null;
		try {
			resultQuery = (Optional<Query>) query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info("resultsetHash is not in query_store yet");
		}
		this.session.getTransaction().commit();
		this.session.close();
		return resultQuery;
	}

}
