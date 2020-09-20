package at.ac.tuwien.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import at.ac.tuwien.api.dto.InsertTableDto;
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

    private SessionFactory sessionFactory;

    @Autowired
    public DataStoreDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(InsertTableDto dto, String query) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(query);

            Map<String, String> columnNamesAndColumnTypes = getColumnNamesAndColumnTypes(dto.getTableName());



//            for (Map<String, String> map : dto.getRecords()) {
//                int i = 1;
//                for (Map.Entry<String, String> entries : map.entrySet()) {
//                    List<String> collect = columnNamesAndColumnTypes.values().stream().collect(Collectors.toList());
//                    collect.get(i+1);
//
//
//                    pstmt.setObject(i, entries.getValue());
//                    i++;
//                }
//            }

//adInt(pstm,value,pos)
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void execute(String query) {
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        }
        return rs;
    }

    public Map<String, String> getColumnNamesAndColumnTypes(String tableName) {
        PreparedStatement stmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Map<String, String> columnsMap = new HashMap<String, String>();
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(
                    "SELECT COLUMN_NAME, DATA_TYPE FROM information_schema.columns WHERE TABLE_NAME=?");
            stmt.setString(1, tableName);
            rs = stmt.executeQuery();

            while (rs.next()) {
                columnsMap.put(rs.getString("COLUMN_NAME"), rs.getString("DATA_TYPE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return columnsMap;
    }

    private Connection getConnection() {
        Connection connection = null;
        Session session = sessionFactory.openSession();
        SessionImpl sessionImpl = (SessionImpl) session;
        connection = sessionImpl.connection();
        return connection;

    }

}
