package ru.javastudy.mvc.core.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.mvc.core.bean.DBLog;
import ru.javastudy.mvc.core.bean.User;

import java.util.List;

@Controller
public class JDBCController {

    @Autowired
    private JDBCExample jdbcExample;

    @RequestMapping(value = "/jdbcQueryAllUsers", method = RequestMethod.GET)
    public ModelAndView jdbcSelectAllUsers() {
        System.out.println("JDBCController jdbcSelectAllUsers() is called");
        List<User> users =  jdbcExample.queryAllUsers();
        return new ModelAndView("/jdbc/jdbc", "resultObject", users);
    }

    @RequestMapping(value = "/jdbcInsert/logstring/{logstring}", method = RequestMethod.GET)
    public ModelAndView jdbcInsert(@PathVariable("logstring") String logstring) {
        System.out.println("JDBCController jdbcInsert is called");
        DBLog dblog = new DBLog();
        dblog.setLOGSTRING(logstring);
        boolean result = jdbcExample.insertLog(dblog);
        return new ModelAndView("/jdbc/jdbc", "resultObject", result);
    }

    @RequestMapping(value = "/jdbcSelectLogs", method = RequestMethod.GET)
    public ModelAndView jdbcSelect() {
        System.out.println("JDBCController jdbcSelect is called");
        List<DBLog> dbLogs =  jdbcExample.queryAllLogs();
        return new ModelAndView("/jdbc/jdbc", "resultObject", dbLogs);

    }

    @RequestMapping(value = "/jdbcDelete/user/{iduser}", method = RequestMethod.GET)
    public ModelAndView jdbcDelete( @PathVariable("iduser") int iduser) {
        System.out.println("JDBCController jdbcDelete is called");
        boolean result = jdbcExample.deleteUSER(iduser);
        return new ModelAndView("/jdbc/jdbc", "resultObject", result);

    }

    @RequestMapping(value = "/jdbcUpdate/user/username/{username}/enabled/{enabled}", method=RequestMethod.GET)
    public ModelAndView jdbcUpdate(@PathVariable("username") String username, @PathVariable("enabled") boolean enabled) {
        System.out.println("JDBCController jdbcUpdate is called");
        User user = new User();
        user.setUsername(username);
        boolean result = jdbcExample.updateUserEnable(user, enabled);
        return new ModelAndView("/jdbc/jdbc", "resultObject", result);
    }
}
