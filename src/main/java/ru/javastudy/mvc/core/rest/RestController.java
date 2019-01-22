package ru.javastudy.mvc.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.javastudy.mvc.core.rest.model.DBLogJSON;
import ru.javastudy.mvc.core.rest.model.DBLogXML;
import ru.javastudy.mvc.core.rest.model.DBLogsJSON;
import ru.javastudy.mvc.core.rest.model.DBLogsXML;
import ru.javastudy.mvc.core.service.DBLogService;

import java.util.List;

/**
 * Created for JavaStudy.ru on 28.02.2016.
 */
@Controller
public class RestController {

    @Autowired
    private DBLogService dbLogService;

    @RequestMapping(value = "/rest/getAllDBLogsXML", method = RequestMethod.GET, produces = "application/xml")
    public @ResponseBody DBLogsXML getAllDBLogsXML() {
        List<DBLogXML> dbLogsList = null;
        try {
            dbLogsList = dbLogService.queryAllDBLogsXML();  //JPA (Hibernate)
//            dbLogsList = dbLogService.queryAllDBLogsJDBCExampleXML(); //JDBC
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dbLogsList);
        DBLogsXML dbLogsXML = new DBLogsXML();
        dbLogsXML.setLogList(dbLogsList);
        return dbLogsXML;
    }

    /** look to mvc-config.xml for <mvc:message-converters>. It can produce 'pretty' json response. */
    @RequestMapping(value = "/rest/getAllDBLogsJSON", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody DBLogsJSON getAllDBLogsJSON() {
        List<DBLogJSON> dbLogsJSONList = null;
        try {
            dbLogsJSONList = dbLogService.queryAllDBLogsJSON();  //JPA (Hibernate)
//            dbLogsJSONList = dbLogService.queryAllDBLogsJDBCExampleJSON(); //JDBC
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dbLogsJSONList);
        DBLogsJSON dbLogsJSON = new DBLogsJSON();
        dbLogsJSON.setLogList(dbLogsJSONList);
        return dbLogsJSON;
    }
}
