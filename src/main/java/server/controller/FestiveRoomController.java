package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.exception.FestiveRoomErrorBooking;
import server.model.Booking;
import server.model.Client;
import server.model.FestiveRoom;
import server.repository.BookingRepository;
import server.repository.ClientRepository;
import server.repository.FestiveRoomRepository;
import server.service.DateService;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ileossa on 23/05/2017.
 */
@RestController
@RequestMapping("/api/room/festive")
public class FestiveRoomController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());


    private FestiveRoomRepository festiveRoomRepository;
    private BookingRepository bookingRepository;
    private DateService dateService;
    private ClientRepository clientRepository;

    @Autowired
    public FestiveRoomController(FestiveRoomRepository festiveRoomRepository, BookingRepository bookingRepository, DateService dateService, ClientRepository clientRepository) {
        this.festiveRoomRepository = festiveRoomRepository;
        this.bookingRepository = bookingRepository;
        this.dateService = dateService;
        this.clientRepository = clientRepository;
    }

    @RequestMapping(method = GET, value = "/all")
    public List<FestiveRoom> getReservationFestiveRoom(){
        return festiveRoomRepository.findAll();
    }



    @RequestMapping(method = GET, value="/getItems")
    @ResponseStatus(OK)
    public List<Booking> getItems(@RequestParam(value="token") String clientToken){
        Client client = clientRepository.findClientByTokenEquals(clientToken);
        return bookingRepository.findBookingByIdClient(client.getClientId());
    }


    @RequestMapping(method = POST, value="book" )
    public float newReservationFestiveRoom(@RequestParam(value="token")String tokenClient,
                                                 @RequestParam(value = "dateStart") String dateStart,
                                                 @RequestParam(value = "dateEnd") String dateEnd,
                                                 @RequestBody FestiveRoom festiveRoom){
        Date startDate = dateService.stringToDate(dateStart);
        Date endDate = dateService.stringToDate(dateEnd);
        List<Booking> books = bookingRepository.findAllByDateBookBetween(startDate, endDate);
        if( books.size() == 0){
            festiveRoomRepository.saveAndFlush(festiveRoom);
            //return festiveRoom.getPrice() * dateService.numberDaysBetween(dateStart, dateEnd);
            return 0;
        } else {
            throw new FestiveRoomErrorBooking();
        }
    }


    @RequestMapping(method = POST, value="{idPartyRoom}" )
    public FestiveRoom updateReservationFestiveRoom(@RequestBody FestiveRoom festiveRoom){
        festiveRoom = festiveRoomRepository.findFestiveRoomByIdPartyRoom(festiveRoom.getIdPartyRoom());
        if( festiveRoom != null){
            festiveRoomRepository.saveAndFlush(festiveRoom);
        }else{
            LOG.error ("Error update festive room, object FestiveRoom find is equal to " + festiveRoom.toString());
        }
        return null;
    }


}
