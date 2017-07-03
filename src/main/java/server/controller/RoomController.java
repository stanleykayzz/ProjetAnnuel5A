package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.Room;
import server.repository.ClientRepository;
import server.repository.RoomRepository;

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

    @Autowired
    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    //todo renvoie la list de tout les room
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


    //update room
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

}
