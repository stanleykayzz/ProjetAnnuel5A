package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.model.Booking;
import server.model.Client;
import server.repository.BookingRepository;
import server.repository.ClientRepository;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by ileossa on 18/05/2017.
 */
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ClientRepository clientRepository;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");


    // TODO : renvoie la liste de tout les booking
    @RequestMapping(method = GET, value="/all")
    public List<Booking> getListBooking(){
        return bookingRepository.findAll();
    }

    /**
     *   renvoie la list de Booking d'un idClient passé en parametre
     * @param token
     * @return
     */
    @RequestMapping(method = GET)
    List<Booking> getListBookingByIdUser(@RequestParam(value = "idClient") String token){
        List<Client> clients = clientRepository.findByToken(token);
        return bookingRepository.findAllByIdClient(clients.get(0).getToken());
    }


    // TODO : enregistrer un nouveau booking
    @RequestMapping(method = POST)
    public Booking newBooking(@RequestParam(value="idClient")int idClient ,
                               @RequestParam(value="idPartyRoom")int idPartyRoom ,
                               @RequestParam(value = "dateStart") String dateStartEntry ,
                               @RequestParam(value = "dateEnd") String dateEndEntry ,
                               @RequestParam(value = "nbPerson") int nbPerson ,
                               @RequestParam(value = "price") float price ,
                               @RequestParam(value = "payementMode") String payementMode){
        //Convertion String to Date
        LocalDate dateStartTemp = LocalDate.parse(dateStartEntry);
        LocalDate dateEndTemp = LocalDate.parse(dateEndEntry);
       // Get current Date + Time + zoneId
        Instant now = Instant.now();
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        ZonedDateTime dateBook = ZonedDateTime.ofInstant(now, zoneId);

        //convert localDate to Date
        Date dateStart = Date.from(dateStartTemp.atStartOfDay(zoneId).toInstant());
        Date dateEnd = Date.from(dateEndTemp.atStartOfDay(zoneId).toInstant());

        Booking booking = new Booking(Date.from(dateBook.toInstant()), dateStart, dateEnd, nbPerson, price, payementMode, idPartyRoom, idClient);
        return bookingRepository.save(booking);
    }



    // TODO : mettre à jour un booking
    @RequestMapping(method = PUT)
    public Booking updateBooking (@RequestParam(value = "idBooking") int idBooking,
                               @RequestParam(value="idClient")int idClient ,
                               @RequestParam(value="idPartyRoom")int idPartyRoom ,
                               @RequestParam(value = "dateStart") String dateStartEntry ,
                               @RequestParam(value = "dateEnd") String dateEndEntry ,
                               @RequestParam(value = "nbPerson") int nbPerson ,
                               @RequestParam(value = "price") float price ,
                               @RequestParam(value = "payementMode") String payementMode){
        if(bookingRepository.findOne(idBooking) != null){

            Booking booking = bookingRepository.findOne(idBooking);

            booking.check(idClient);
            booking.check(idPartyRoom);
            booking.check(stringToDate(dateStartEntry));
            booking.check(stringToDate(dateEndEntry));
            booking.check(nbPerson);
            booking.check(price);
            booking.check(payementMode);

            return booking;
        }
        return new Booking();
    }



// ------------------------------------------------------------------

    private Date stringToDate(String dateEntry) {
        LocalDate dateLocal = LocalDate.parse(dateEntry);
        // Get current Date + Time + zoneId
        ZoneId zoneId = ZoneId.of("Europe/Paris");

        //convert localDate to Date
        return Date.from(dateLocal.atStartOfDay(zoneId).toInstant());
    }





}










































