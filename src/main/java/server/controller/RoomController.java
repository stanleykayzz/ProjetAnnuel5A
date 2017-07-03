package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.model.Booking;
import server.model.Building;
import server.model.Room;
<<<<<<< HEAD
=======
import server.repository.BookingRepository;
import server.repository.BuildingRepository;
>>>>>>> update  room logic (pojo, controller)
import server.repository.RoomRepository;
import server.service.DateService;
import server.service.mail.BookingService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @Autowired
    public RoomController(RoomRepository roomRepository, BookingRepository bookingRepository, DateService dateService, BookingService bookingService, BuildingRepository buildingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.dateService = dateService;
        this.bookingService = bookingService;
        this.buildingRepository = buildingRepository;
    }


    @RequestMapping(method = GET, value = "/all")
    public List<Room> getRoomsBooking(){
        return roomRepository.findAll();
    }

    //todo renvoie la room avec roomId
    @RequestMapping(method = GET, value = "/client/{idClient}")
    public List<Room> getRoomsBookingByUserId(@PathVariable int idClient){
        return roomRepository.findAllByIdClient(idClient);
    }


    // todo renvoie la room avec userId
    @RequestMapping(method = GET, value="/room/{idRoom}")
    public List<Room> getRoomsByRoomId(@PathVariable int idRoom){
        return roomRepository.findAllByIdRoom(idRoom);
    }

    @RequestMapping(method = POST)
    public Room newBookingRoom(@PathVariable String name,
                               @PathVariable String building,
                               @PathVariable int number,
                               @PathVariable int idClient){
        Room room = roomRepository.findByIdClient(idClient);
        if (room == null){
            room.setIdClient(idClient);
            room.setName(name);
            room.setNumber(number);
            room.setBuilding(building);

        }else {
            LOG.error("Booking exist in BDD");
            return room;
        }
        return room;
    }


    @RequestMapping(method = POST, value="{idRoom}")
    public Room updateBookingRoom(@PathVariable int idRoom,
                                  @PathVariable String name,
                                  @PathVariable String building,
                                  @PathVariable int number,
                                  @PathVariable int idClient){
        Room room = roomRepository.findByIdRoom(idRoom);
        if (room != null){
            room.setNumber(number);
            room.setName(name);
            room.setBuilding(building);
            room.setIdClient(idClient);

            room = roomRepository.save(room);
            LOG.info("booking number: "+idRoom+" update in bdd, object: " + room.toString());
        }else{
            LOG.error("Booking room doest not exist in BDD");
        }
        return room;
    }


    // [API] Requete recherche de chambres par:                            token
    // date_debut_reservation date_fin_reservation    type_chambre

    @RequestMapping(method =POST ,value= "/free")
    public List<Room> searchRoomFree(@RequestParam(value = "begin_date") String begin_date,
                                     @RequestParam(value = "end_date") String end_date,
                                     @RequestParam(value = "type_room") String typeRoom){

        List<Room> rooms = new ArrayList<>();
        if(typeRoom.toUpperCase().equals("all")) {
            rooms = roomRepository.findAllByCategoryRoomEquals(typeRoom);
        }else{
            rooms = roomRepository.findAllByCategoryRoomEquals(typeRoom);
        }
        //recuperer les room occupe pendant la duree
        List<Booking> bookings = bookingRepository.findAllByDateBookBetween(dateService.stringToDate(begin_date));
        // enlever de la liste room les chambre prisent
        for (Booking book : bookings){
            if(bookingService.isFree(book)){
                rooms.remove(book.getIdRoomForClient());
            }
        }
        return rooms;
    }



    @RequestMapping(method = GET, value="/${building}")
    public List<Room> searchRoomByBuilding(@PathVariable String building){
        Building b1 = buildingRepository.findBuildingByNameBuildEquals(building);
        List<Room> rooms = roomRepository.findAllByIdBuildingEquals(b1.getIdBuilding());
        return rooms;
    }

}
