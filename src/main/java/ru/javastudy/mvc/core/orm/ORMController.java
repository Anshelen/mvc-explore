package ru.javastudy.mvc.core.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.mvc.core.bean.User;

import java.util.List;

/**
 * Created for JavaStudy.ru on 28.02.2016.
 */
@Controller
public class ORMController {

    @Autowired
    private ORMService ormService;

    @RequestMapping(value = "/ormFindAllUsers", method= RequestMethod.GET)
    public ModelAndView ormFindAllUsers() {
        System.out.println("ORMController ormFindAllUsers is called");
        List<User> users = ormService.queryFindAllUsersJPA();
        return new ModelAndView("/orm/orm", "resultObject", users);
    }

    @RequestMapping(value = "/queryFindByIdUser/{userid}", method = RequestMethod.GET)
    public ModelAndView queryFindByIdUser(@PathVariable("userid") int userid) {
        System.out.println("ORMController queryFindByIdUser is called");
        User user = ormService.queryFindUserById(userid);
        return new ModelAndView("/orm/orm", "resultObject", user);
    }

    @RequestMapping(value = "/ormUpdateUser/iduser/{iduser}/enabled/{enabled}", method=RequestMethod.GET)
    public ModelAndView ormUpdateUser(
        @PathVariable(value="iduser") int iduser,
        @PathVariable(value="enabled") boolean enabled) {
        System.out.println("ORMController ormUpdateUser is called");
        boolean result = ormService.updateUser(iduser, enabled);
        return new ModelAndView("/orm/orm", "resultObject", result);
    }
    @RequestMapping(value = "/ormDeleteUser/iduser/{iduser}", method=RequestMethod.GET)
    public ModelAndView ormDeleteUser(@PathVariable(value="iduser") int iduser) {
        System.out.println("ORMController jdbcDelete is called");
        boolean result = ormService.deleteUser(iduser);
        return new ModelAndView("/orm/orm", "resultObject", result);
    }

    @RequestMapping(value = "/ormInsertUser/username/{username}/password/{password}/enabled/{enabled}"
        , method=RequestMethod.GET)
    public ModelAndView ormInsertUser(
        @PathVariable(value="username") String username,
        @PathVariable(value="password") String password,
        @PathVariable(value="enabled") boolean enabled) {
        System.out.println("ORMController ormInsertUser is called");
        boolean result = ormService.insertUser(username, password, enabled);
        return new ModelAndView("/orm/orm", "resultObject", result);

    }
}
