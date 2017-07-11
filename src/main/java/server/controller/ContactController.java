package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.service.mail.MailService;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ileossa on 09/07/2017.
 */
@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    private MailService mailService;

    @Autowired
    public ContactController(MailService mailService) {
        this.mailService = mailService;
    }


    @RequestMapping(method = POST)
    @ResponseStatus(OK)
    public void sendMailToContactFormulaire(@RequestParam(value = "message") String message, @RequestParam(value = "email") String email, @RequestParam(value = "title")String title){
        mailService.sendEmail(email, title, message);
    }
}
