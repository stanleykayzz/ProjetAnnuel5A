package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import server.model.Booking;
import server.model.CategoryRoom;
import server.model.Client;
import server.repository.BookingRepository;
import server.repository.CategoryRoomRepository;
import server.repository.ClientRepository;
import server.repository.RoomRepository;
import server.service.DateService;
import server.service.mail.MailService;

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
@RequestMapping("/api/booking")
public class BookingController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private BookingRepository bookingRepository;
    private ClientRepository clientRepository;
    private MailService mailService;
    private DateService dateService;
    private CategoryRoomRepository categoryRoomRepository;
    private RoomRepository roomRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository, ClientRepository clientRepository, MailService mailService, DateService dateService, CategoryRoomRepository categoryRoomRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.mailService = mailService;
        this.dateService = dateService;
        this.categoryRoomRepository = categoryRoomRepository;
        this.roomRepository = roomRepository;
    }

    @Value("${booking.timezone}")
    String zoneIdForUtcOffset;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");


    @RequestMapping(method = GET, value="/all")
    public List<Booking> getListBooking(){
        return bookingRepository.findAll();
    }

    /**
     *   renvoie la list de Booking d'un idClient passé en parametre
     * @param token
     * @return
     */
    @RequestMapping(method = GET, value="{token}")
    List<Booking> getListBookingByIdUser(@PathVariable String token){
        List<Client> clients = clientRepository.findByToken(token);
        return bookingRepository.findAllByTokenId(clients.get(0).getToken());
    }


    // TODO : envoyer un email de confirmation
    @RequestMapping(method = POST)
    public Booking newBooking(@RequestParam(value="tokenCLient")String tokenClient ,
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
        ZoneId zoneId = ZoneId.of(zoneIdForUtcOffset);
        ZonedDateTime dateBook = ZonedDateTime.ofInstant(now, zoneId);

        //convert localDate to Date
        Date dateStart = Date.from(dateStartTemp.atStartOfDay(zoneId).toInstant());
        Date dateEnd = Date.from(dateEndTemp.atStartOfDay(zoneId).toInstant());

        Booking booking = new Booking(Date.from(dateBook.toInstant()),
                dateStart,
                dateEnd,
                nbPerson,
                price,
                payementMode,
                idPartyRoom,
                tokenClient);
        Booking result =  bookingRepository.save(booking);
        Client client = clientRepository.findByToken(tokenClient).get(0);
        mailService.sendEmail(client, booking, "booking registry", "booking registry");
        return result;
    }



    @RequestMapping(method = PUT)
    public Booking updateBooking (@RequestParam(value = "idBooking") int idBooking,
                               @RequestParam(value="tokenClient")String tokenClient ,
                               @RequestParam(value="idPartyRoom")int idPartyRoom ,
                               @RequestParam(value = "dateStart") String dateStartEntry ,
                               @RequestParam(value = "dateEnd") String dateEndEntry ,
                               @RequestParam(value = "nbPerson") int nbPerson ,
                               @RequestParam(value = "price") float price ,
                               @RequestParam(value = "payementMode") String payementMode){
        if(bookingRepository.findOne(idBooking) != null){

            Booking booking = bookingRepository.findOne(idBooking);

            booking.check(tokenClient);
            booking.check(idPartyRoom);
            booking.check(dateService.stringToDate(dateStartEntry));
            booking.check(dateService.stringToDate(dateEndEntry));
            booking.check(nbPerson);
            booking.check(price);
            booking.check(payementMode);
            Client client = clientRepository.findByToken(tokenClient).get(0);
            mailService.sendEmail(client, booking, "reservation updated", "reservation_updated.vm");
            return booking;
        }
        return new Booking();
    }




    @RequestMapping(method = GET)
    public double costByNight(@RequestParam(value="date_start") String dateStartEnter,
                           @RequestParam(value = "date_end") String dateEndEnter,
                           @RequestParam(value = "reservation_type") String typeReservation){

        // recup le liste des type avec prix
        List<CategoryRoom> categories = categoryRoomRepository.findAll();
        for(CategoryRoom one : categories){
            if(one.getName().equals(typeReservation)){
                Date dateStart = dateService.stringToDate(dateStartEnter);
                Date dateEnd = dateService.stringToDate(dateEndEnter);
                long diff = dateEnd.getTime() - dateStart.getTime();
                return diff * one.getCostByNight();
            }
        }
        return Double.MAX_VALUE;
    }








}
