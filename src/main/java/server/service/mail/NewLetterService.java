package server.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import server.model.Booking;
import server.model.Client;
import server.model.NewsLetter;
import server.repository.BookingRepository;
import server.repository.ClientRepository;
import server.repository.NewsLetterRepository;
import server.service.DateService;

import java.util.List;

/**
 * Created by ileossa on 03/07/2017.
 */
@Service
public class NewLetterService {

    private ClientRepository clientRepository;
    private NewsLetterRepository newsLetterRepository;
    private MailService mailService;
    private BookingRepository bookingRepository;
    private DateService dateService;

    @Autowired
    public NewLetterService(ClientRepository clientRepository, NewsLetterRepository newsLetterRepository, MailService mailService, BookingRepository bookingRepository, DateService dateService) {
        this.clientRepository = clientRepository;
        this.newsLetterRepository = newsLetterRepository;
        this.mailService = mailService;
        this.bookingRepository = bookingRepository;
        this.dateService = dateService;
    }

    @Scheduled(cron="0 0 7 * * *")  //Every days at 7 AM, this cron launched
    public void sendNewsLetter(){
        List<NewsLetter> listSendMail = newsLetterRepository.findAllBySendNewsLetterEquals(false);
        for (NewsLetter letter : listSendMail){
            if(letter.isSendNewsLetter()){
                Client client = clientRepository.findClientById(letter.getIdClient());
                mailService.sendEmail(client, "La Résidence des hauts de Menaye", "news_letter.vm");
            }
        }
    }

    @Scheduled(cron= "0 0 0 * * *")
    public void sendEvaluation(){
        List<Booking> bookings = bookingRepository.findAllByDateEndIsAfter(dateService.currentLocalTime());
        for (Booking book : bookings){
            if(book.isSendEvaluation()){
                Client client = clientRepository.findDistinctFirstByToken(book.getIdClient());
                mailService.sendEmail(client, "La Résidence des hauts de Menaye", "evalution.vm");
                book.setSendEvaluation(false);
                bookingRepository.saveAndFlush(book);
            }
        }
    }
}
