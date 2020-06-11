package at.ac.tuwien.persistence.impl;

//@Repository
//public class DatabaseDaoImpl implements DatabaseDao {
//	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseDaoImpl.class);
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	@Override
//	public void executeQuery(String query) {
//		jdbcTemplate.setDataSource(SpringJdbcConfig.getDataSource(""));
//
//		try {
//			jdbcTemplate.execute(query);
//		} catch (DataAccessException e) {
//			LOGGER.error("There is a Problem with execute: " + e.getMessage());
//		}
//	}
//
//	public List<String> getDatabases() {
//		jdbcTemplate.setDataSource(SpringJdbcConfig.getDataSource(""));
//		Statement stmt;
//		ResultSet rs;
//		List<String> databases = new ArrayList<String>();
//		try {
//			stmt = jdbcTemplate.getDataSource().getConnection().createStatement();
//			rs = stmt.executeQuery("SELECT datname FROM pg_database;");
//			while (rs.next()) {
//				databases.add(rs.getString("datname"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return databases;
//	}
//
//}
