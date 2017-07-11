package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.Booking;
import server.model.Client;
import server.model.NewsLetter;
import server.repository.BookingRepository;
import server.repository.ClientRepository;
import server.repository.NewsLetterRepository;

/**
 * Created by ileossa on 03/07/2017.
 */
@RestController
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
        Client client = clientRepository.findClientByEmailEquals(email);
        NewsLetter letter = newsLetterRepository.findNewsLetterById(client.getId());
        letter.setSendNewsLetter(false);
        newsLetterRepository.saveAndFlush(letter);
        return "Description to the newsletter has been well recorded. The cancellation treatment may take a few days.";
    }

    @RequestMapping(value = "/rate/${email}/${nb}")
    public String getRateByClient(@PathVariable String email,
                                  @PathVariable int nb){
        Client client = clientRepository.findClientByEmailEquals(email);
        Booking book = bookingRepository.findBookingById(client.getId());
        book.setRate(nb);
        bookingRepository.saveAndFlush(book);
        return "redirect:index.html";
    }

}
