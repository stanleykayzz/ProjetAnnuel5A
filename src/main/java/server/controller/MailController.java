package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import server.model.Client;
import server.model.NewsLetter;
import server.repository.BookingRepository;
import server.repository.ClientRepository;
import server.repository.NewsLetterRepository;

import java.awt.print.Book;
import java.util.List;

/**
 * Created by ileossa on 03/07/2017.
 */
@RequestMapping(value = "api/mail/")
public class MailController {


    private NewsLetterRepository newsLetterRepository;
    private ClientRepository clientRepository;
    private BookingRepository bookingRepository;

    @Autowired
    public MailController(NewsLetterRepository newsLetterRepository, ClientRepository clientRepository, BookingRepository bookingRepository) {
        this.newsLetterRepository = newsLetterRepository;
        this.clientRepository = clientRepository;
        this.bookingRepository = bookingRepository;
    }


    @RequestMapping(value = "/unsubscribe/${email}")
    public String unsucribe(@PathVariable String email){
        List<Client> client = clientRepository.findClientByEmailEquals(email);
        NewsLetter letter = newsLetterRepository.findNewsLetterByIdClientEquals(client.get(0).getClientId());
        letter.setSendNewsLetter(false);
        newsLetterRepository.saveAndFlush(letter);
        return "Description to the newsletter has been well recorded. The cancellation treatment may take a few days.";
    }

    @RequestMapping(value = "/rate/${email}/${nb}")
    public void getRateByClient(@PathVariable String email,
                                @PathVariable int nb){
        //todo faire le service
    }

}
