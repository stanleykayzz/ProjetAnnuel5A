package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import server.model.Client;
import server.model.NewsLetter;
import server.repository.BookingRepository;
import server.repository.ClientRepository;
import server.repository.NewsLetterRepository;
import server.service.ClientService;
import server.service.mail.MailService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ileossa on 09/07/2017.
 */
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
        if(clientService.isAuthorized(tokenCLient)){
            newsLetterRepository.saveAndFlush(newsLetter);
            List<Client> clients = clientRepository.findAll();
            for (Client client: clients) {
                //check if reason booking == newsletter reason  => true(send) / false(skip)s
                if(bookingRepository.findOne(client.getClientId()).getReason().equals(newsLetter.getReason())) {
                    mailService.sendEmail(client, "Newsletter", "news_letter.vm");
                }
            }
        }
    }
}
