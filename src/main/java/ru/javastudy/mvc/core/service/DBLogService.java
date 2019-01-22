package ru.javastudy.mvc.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.javastudy.mvc.core.rest.model.DBLogJSON;
import ru.javastudy.mvc.core.rest.model.DBLogXML;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created for JavaStudy.ru on 05.03.2016.
 */
@Service
public class DBLogService {

    @PersistenceContext
    private EntityManager entityManager;

    /* or you can use JDBCTemplate instead JPA */
    private JdbcTemplate jdbcTemplate;
    /* if use JDBCTemplate */
    @Autowired
    public DBLogService (DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //JPA XML example
    public List<DBLogXML> queryAllDBLogsXML() {
        System.out.println("DBLogService queryAllDBLogsXML() is called");
        /*important! We use entity class DBLogXML instead table name LOG (not 'from LOG')*/
        return entityManager.createQuery("from DBLogXML", DBLogXML.class).getResultList();
    }

    //JPA JSON example
    public List<DBLogJSON> queryAllDBLogsJSON() {
        System.out.println("DBLogService queryAllDBLogsJSON() is called");
        /*important! We use entity class DBLogJSON instead table name LOG (not 'from LOG')*/
        return entityManager.createQuery("from DBLogJSON", DBLogJSON.class).getResultList();
    }

    //JdbcTemplate query with in method RowMapper example (XML)
    public List<DBLogXML> queryAllDBLogsJDBCExampleXML() {
        System.out.println("DBLogService queryAllDBLogsJDBCExampleXML() is called");
        final String querySQL = "SELECT * FROM LOG";
        List<DBLogXML> dbLogs = jdbcTemplate.query(querySQL, new RowMapper<DBLogXML>() {
            @Override
            public DBLogXML mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBLogXML dbLog = new DBLogXML();
                dbLog.setIDLOG(resultSet.getInt("IDLOG"));
                dbLog.setLOGSTRING(resultSet.getString("LOGSTRING"));
                return dbLog;
            }
        });
        return dbLogs;
    }

    //JdbcTemplate query with in method RowMapper example (JSON)
    public List<DBLogJSON> queryAllDBLogsJDBCExampleJSON() {
        System.out.println("DBLogService queryAllDBLogsJDBCExampleJSON() is called");
        final String querySQL = "SELECT * FROM LOG";
        List<DBLogJSON> dbLogs = jdbcTemplate.query(querySQL, new RowMapper<DBLogJSON>() {
            @Override
            public DBLogJSON mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBLogJSON dbLog = new DBLogJSON();
                dbLog.setIDLOG(resultSet.getInt("IDLOG"));
                dbLog.setLOGSTRING(resultSet.getString("LOGSTRING"));
                return dbLog;
            }
        });
        return dbLogs;
    }



}