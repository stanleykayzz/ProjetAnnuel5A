package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import server.model.Client;
import server.model.NewsLetter;
import server.repository.ClientRepository;
import server.repository.NewsLetterRepository;

/**
 * Created by ileossa on 03/07/2017.
 */
@RequestMapping(value = "api/describe/")
public class MailController {


    private NewsLetterRepository newsLetterRepository;
    private ClientRepository clientRepository;

    @Autowired
    public MailController(NewsLetterRepository newsLetterRepository, ClientRepository clientRepository) {
        this.newsLetterRepository = newsLetterRepository;
        this.clientRepository = clientRepository;
    }


    @RequestMapping(value = "/${email}")
    public String unsucribe(@PathVariable String email){
        Client client = clientRepository.findClientByEmailEquals(email);
        NewsLetter letter = newsLetterRepository.findNewsLetterByIdClientEquals(client.getClientId());
        letter.setSendNewsLetter(false);
        newsLetterRepository.save(letter);
        return "Description to the newsletter has been well recorded. The cancellation treatment may take a few days.";
    }

}
