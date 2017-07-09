package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.exception.TokenError;
import server.model.*;
import server.repository.BookingRepository;
import server.repository.BuildingRepository;
import server.repository.CategoryRoomRepository;
import server.repository.RoomRepository;
import server.service.ClientService;
import server.service.DateService;
import server.service.BookingService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static server.model.Enum.Statut.CANCELED;

/**
 * Created by ileossa on 23/05/2017.
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private RoomRepository roomRepository;
    private BookingRepository bookingRepository;
    private DateService dateService;
    private BookingService bookingService;
    private BuildingRepository buildingRepository;
    private CategoryRoomRepository categoryRoomRepository;
    private ClientService clientService;

    @Autowired
    public RoomController(RoomRepository roomRepository, BookingRepository bookingRepository, DateService dateService, BookingService bookingService, BuildingRepository buildingRepository, CategoryRoomRepository categoryRoomRepository, ClientService clientService) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.dateService = dateService;
        this.bookingService = bookingService;
        this.buildingRepository = buildingRepository;
        this.categoryRoomRepository = categoryRoomRepository;
        this.clientService = clientService;
    }


    @RequestMapping(method = GET)
    public List<Room> getRoomsBooking(){
        return roomRepository.findAll();
    }

    @RequestMapping(method = GET, value = "/client/{idClient}")
    public List<Room> getRoomsBookingByUserId(@PathVariable int idClient,
                                              @RequestParam(value = "token")String tokenClient) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            return roomRepository.findAllByIdClient(idClient);
        }
        throw new TokenError();
    }

    @RequestMapping(method = GET, value="/room/{idRoom}")
    public List<Room> getRoomsByRoomId(@PathVariable int idRoom,
                                       @RequestParam(value = "token")String tokenClient) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
          return roomRepository.findAllByIdRoom(idRoom);
        }
        throw new TokenError();
    }

    @RequestMapping(method = GET, value="/${building}")
    public List<Room> searchRoomByBuilding(@PathVariable String building,
                                           @RequestParam(value = "token")String tokenClient) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            Building b1 = buildingRepository.findBuildingByNameBuildEquals(building);
            List<Room> rooms = roomRepository.findAllByIdBuildingEquals(b1.getIdBuilding());
            return rooms;
        }
        throw new TokenError();
    }


    @RequestMapping(method =GET ,value= "/search")
    public List<Room> searchRoomFree(@RequestParam(value = "date_start") String begin_date,
                                     @RequestParam(value = "date_end") String end_date,
                                     @RequestParam(value = "type") String nameTypeCategoryRoom,
                                     @RequestParam(value = "token")String tokenClient) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            CategoryRoom typeRoom = categoryRoomRepository.findCategoryRoomByName(nameTypeCategoryRoom);
            List<Room> rooms = new ArrayList<>();
            if(typeRoom.getName().toUpperCase().equals("all")) {
                rooms = roomRepository.findAllByCategoryRoom(typeRoom);
            }else{
                rooms = roomRepository.findAllByCategoryRoom(typeRoom);
            }
            //recuperer les room occupe pendant la duree
            List<Booking> bookings = bookingRepository.findAllByDateBookBetween(dateService.stringToDate(begin_date), dateService.stringToDate(end_date));
            // enlever de la liste room les chambre prisent
            for (Booking book : bookings){
                if(bookingService.isFree(book)){
                    rooms.remove(book.getIdRoomForClient());
                }
            }
            return rooms;
        }
        throw new TokenError();
    }


    @RequestMapping(method = GET, value = "/category/all")
    public List<CategoryRoom> getAllCategoriesRoom(@RequestParam(value = "token")String tokenClient) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            return categoryRoomRepository.findAll();
        }
        throw new TokenError();
    }


    @RequestMapping(method = POST)
    @ResponseStatus(OK)
    public void newRoom(@RequestParam(value="token")String tokenCLient,
                        @RequestBody Room room) throws TokenError {
        if(clientService.isAuthorized(tokenCLient)) {
            if(clientService.isAuthorized(tokenCLient)) {
                roomRepository.saveAndFlush(room);
            }
        }
        throw new TokenError();
    }



    @RequestMapping(method = PUT)
    @ResponseStatus(OK)
    public Room updateBookingRoom(@RequestParam(value = "token") String tokenClient,
                                  @RequestBody Room room) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            room = roomRepository.findByIdRoom(room.getIdRoom());
            if (room != null) {
                room = roomRepository.saveAndFlush(room);
                LOG.info("booking number: " + room.getIdRoom() + " update in bdd, object: " + room.toString());
            } else {
                LOG.error("Booking room doest not exist in BDD");
            }
            return room;
        }
        throw new TokenError();
    }



    @RequestMapping(method = DELETE, value="/cancel")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cancelBook(@RequestParam(value="token") String tokenClient) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            List<Booking> books = bookingRepository.findAllByTokenId(tokenClient);
            for (Booking book : books){
                book.setStatut(CANCELED);
                bookingRepository.saveAndFlush(book);
            }
        }
        throw new TokenError();
    }


}
