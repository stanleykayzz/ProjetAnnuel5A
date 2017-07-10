package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.model.Client;
import server.model.NewsLetter;
import server.repository.BookingRepository;
import server.repository.ClientRepository;
import server.repository.NewsLetterRepository;
import server.service.client.ClientService;
import server.service.mail.MailService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ileossa on 09/07/2017.
 */
@RestController
@RequestMapping("")
public class NewsLetterController {

    private NewsLetterRepository newsLetterRepository;
    private ClientRepository clientRepository;
    private ClientService clientService;
    private MailService mailService;
    private BookingRepository bookingRepository;

    @Autowired
    public NewsLetterController(NewsLetterRepository newsLetterRepository, ClientRepository clientRepository, ClientService clientService, MailService mailService, BookingRepository bookingRepository) {
        this.newsLetterRepository = newsLetterRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
        this.mailService = mailService;
        this.bookingRepository = bookingRepository;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(OK)
    public void newModel(@RequestParam(value="token")String tokenCLient,
                         @RequestBody NewsLetter newsLetter){
        if(clientService.isAdministator(tokenCLient)){
            newsLetterRepository.saveAndFlush(newsLetter);
            List<Client> clients = clientRepository.findAll();
            for (Client client: clients) {
                //check if reason booking == newsletter reason  => true(send) / false(skip)s
                if(bookingRepository.findOne(client.getId()).getReason().equals(newsLetter.getReason())) {
                    mailService.sendEmail(client, "Newsletter", "news_letter.vm");
                }
            }
        }
    }
}
