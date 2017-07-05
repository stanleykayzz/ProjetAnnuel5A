package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.model.Client;
import server.service.mail.MailService;

import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by ileossa on 22/05/2017.
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private MailService mailService;

    @RequestMapping(method = GET, value = "/mail")
    public String hello(){
        Client riri = new Client("riri", "duck", new Date(100009), "riri@localhost", "0616657098", "Disney", "duck city", "12 av disney", "190183", "secretpassword", null, null);
        mailService.sendEmail(riri, "test connard", "account_update.vm");
        return "sending";
    }
}