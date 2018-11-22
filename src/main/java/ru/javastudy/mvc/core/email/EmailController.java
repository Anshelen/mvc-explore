package ru.javastudy.mvc.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/email/send", method = RequestMethod.POST)
    public ModelAndView email(@ModelAttribute("emailModel") EmailModel emailModel) {
        System.out.println("EmailController email is called");
        Map<String, Object> model = new HashMap<>();
        model.put("from", "anshelen2@gmail.com");
        model.put("subject", "Hello from " + emailModel.getName() + '!');
        model.put("to", emailModel.getEmail());
        model.put("ccList", new ArrayList<>());
        model.put("bccList", new ArrayList<>());
        model.put("userName", "javastudyUser");
        model.put("urljavastudy", "javastudy.ru");
        model.put("message", emailModel.getMessage());
        boolean result = emailService.sendEmail("registered.vm", model);
        //use redirect or you will send email after every refresh page.
        return new ModelAndView("redirect:/email.html", "resultSending", result);
    }


}
