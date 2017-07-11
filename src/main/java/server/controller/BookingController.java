package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.exception.InternalError;
import server.exception.TokenError;
import server.model.*;
import server.model.Enum.Reason;
import server.model.Enum.Statut;
import server.repository.*;
import server.service.DateService;
import server.service.client.ClientService;
import server.service.mail.MailService;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ileossa on 18/05/2017.
 */
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private BookingRepository bookingRepository;
    private ClientService clientService;
    private ClientRepository clientRepository;
    private DateService dateService;
    private RoomRepository roomRepository;
    private FestiveRoomRepository festiveRoomRepository;
    private MailService mailService;
    private RestaurantRepository restaurantRepository;
    private ServicesHotelRepository servicesHotelRepository;



    @Autowired
    public BookingController(BookingRepository bookingRepository, ClientService clientService, ClientRepository clientRepository, DateService dateService, RoomRepository roomRepository, FestiveRoomRepository festiveRoomRepository, MailService mailService, RestaurantRepository restaurantRepository) {
        this.bookingRepository = bookingRepository;
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.dateService = dateService;
        this.roomRepository = roomRepository;
        this.festiveRoomRepository = festiveRoomRepository;
        this.mailService = mailService;
        this.restaurantRepository = restaurantRepository;
    }


    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    public List<Booking> getListAll(){
        return bookingRepository.findAll();
    }

    @RequestMapping(method = GET, value="/adminGetList")
    @ResponseStatus(OK)
    public List<Booking> getListIsAdministrator(@RequestParam(value = "token") String token) throws TokenError {
        if(clientService.isAdministator(token)) {
            return bookingRepository.findAll();
        }
        throw new TokenError();
    }

    @RequestMapping(method = GET, value = "/type")
    @ResponseStatus(OK)
    public HashSet<Booking> getBookPerType(@RequestParam(value="token") String tokenUser,
                                           @RequestParam(value = "type") String typeReservation){
        HashSet<Booking> listeFinal =  new HashSet<>();
        if(clientService.isUser(tokenUser)){
            List<Booking> books = bookingRepository.findAll();
            for (Booking booking: books) {
                if(typeReservation.equals("room")){
                        if(booking.getRooms() != null){
                            listeFinal.add(booking);
                        }
                }if(typeReservation.equals("restaurant")){
                        if(booking.getTableRestaurantiD() != null){
                            listeFinal.add(booking);
                        }
                }if(typeReservation.equals("FestiveRoom")){
                    if(booking.getFestiveRoomId() != null){
                        listeFinal.add(booking);
                    }
                }if(typeReservation.equals("serviceHotel")){
                    if(booking.getServiceHotelId() != null){
                        listeFinal.add(booking);
                    }
                }
            }
        }
        return listeFinal;
    }

    @RequestMapping(method = GET, value = "/futur")
    @ResponseStatus(OK)
    public List<Booking> getListBookingInFutur(@RequestParam(value="token") String tokenUser) throws TokenError {
        if(clientService.isUser(tokenUser)){
            Client user = clientRepository.findClientByTokenEquals(tokenUser);
            return bookingRepository.findAllByIdClient(user.getId());
        }
        if(clientService.isAdministator(tokenUser)){
            return bookingRepository.findAllByOrderByDateEndAsc(dateService.currentLocalTime());
        }
        throw new TokenError();
    }

    @RequestMapping(method = GET, value = "/past")
    @ResponseStatus(OK)
    public List<Booking> getListBookingPast(@RequestParam(value = "token") String tokenUser) throws TokenError {
        if(clientService.isUser(tokenUser)){
            Client user = clientRepository.findClientByTokenEquals(tokenUser);
            return bookingRepository.findAllByIdClient(user.getId());
        }
        if(clientService.isAdministator(tokenUser)){
            return bookingRepository.findAllByOrderByDateEndAsc(dateService.currentLocalTime());
        }
        throw new TokenError();
    }


    @RequestMapping(method = POST)
    @ResponseStatus(ACCEPTED)
    public void newBooking(@RequestParam(value="token") String tokenClient,
                           @RequestParam(value="idRoom") int idRoom,
                           @RequestParam(value="idFestiveRoom") int idFestiveRoom,
                           @RequestParam(value="idRestaurant") int idRestaurant,
                           @RequestParam(value="idServices") int idServices,
                           @RequestBody Booking booking) throws TokenError, InternalError {
        String pathPdf = null;
        if(clientService.isUser(tokenClient)){
            //todo doit attribuer une chambre de libre (essayer de regrouper les chambre)
            Client client = clientRepository.findClientByTokenEquals(tokenClient);
            Room room = roomRepository.findOne(idRoom);
            FestiveRoom festiveRoom = festiveRoomRepository.findOne(idFestiveRoom);
            Restaurant restaurant = restaurantRepository.findOne(idRestaurant);
            ServicesHotel servicesHotel = servicesHotelRepository.findOne(idServices);

            booking.setRooms(Arrays.asList(room));
            booking.setFestiveRoomId(Arrays.asList(festiveRoom));
            booking.setTableRestaurantiD(Arrays.asList(restaurant));
            booking.setServiceHotelId(Arrays.asList(servicesHotel));

            Booking test = bookingRepository.saveAndFlush(booking);
            if(test != null) {
                if (booking.getReason().equals(Reason.VACANCY)) {
                    mailService.sendEmailWithAttachement(client, booking, "Booking confirmation", "booking_registry_vacancy.vm", pathPdf);
                }
                if (booking.getReason().equals(Reason.WORK)) {
                    mailService.sendEmailWithAttachement(client, booking, "Booking confirmation", "booking_registry_work.vm", pathPdf);
                }
            }else{
                throw new InternalError();
            }
        }
        throw  new TokenError();
    }


    @RequestMapping(method = DELETE)
    @ResponseStatus(ACCEPTED)
    public void cancelBook(@RequestParam(value="token")String tokenUser,
                           @RequestParam(value = "id") int idBooking) throws TokenError {
        if(clientService.accesPerTokenValidate(tokenUser)){
            Client client = clientRepository.findClientByTokenEquals(tokenUser);
            Booking booked = bookingRepository.findBookingById(client.getId());
            booked.setStatut(Statut.CANCELED);
            bookingRepository.saveAndFlush(booked);
        }
        throw new TokenError();

    }








}
