package ru.javastudy.mvc.core.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RedirectController {

    //redirect to external URL
    @RequestMapping(value = "/redirectExample", method = RequestMethod.GET)
    public String redirectExample(HttpServletRequest request) {
        //request.getScheme() - if you don't know where was the request sent: http, https, ftp..
        return "redirect:" + request.getScheme() +"://javastudy.ru";
    }
}
