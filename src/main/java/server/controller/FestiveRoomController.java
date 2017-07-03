package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.model.FestiveRoom;
import server.repository.ClientRepository;
import server.repository.FestiveRoomRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ileossa on 23/05/2017.
 */
//todo test si 2 festive room
@RestController
@RequestMapping("/api/room/festive")
public class FestiveRoomController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());


    private static FestiveRoomRepository festiveRoomRepository;

    @Autowired
    public FestiveRoomController(FestiveRoomRepository festiveRoomRepository) {
        this.festiveRoomRepository = festiveRoomRepository;
    }

    @RequestMapping(method = GET, value = "/all")
    public List<FestiveRoom> getReservationFestiveRoom(){
        return festiveRoomRepository.findAll();
    }


    @RequestMapping(method = GET, value = "/{idClient}")
    public FestiveRoom getReservationFestiveRoomByClientId(@PathVariable int idClient){
        return festiveRoomRepository.findByIdClient(idClient);

    }

    @RequestMapping(method = POST )
    public FestiveRoom newReservationFestiveRoom(@RequestParam(value="event") String event,
                                                 @RequestParam(value="nbChairs") int nbChairs,
                                                 @RequestParam(value="nbTable") int nbTable,
                                                 @RequestParam(value="idClient") int idCLient){
        FestiveRoom festiveRoom = festiveRoomRepository.findByIdClient(idCLient);
        if( festiveRoom == null){
            festiveRoom.setEvent(event);
            festiveRoom.setNumberTables(nbTable);
            festiveRoom.setNumberChairs(nbChairs);
            festiveRoom.setIdClient(idCLient);
            festiveRoomRepository.save(festiveRoom);
            return festiveRoom;
        }else{
            LOG.error("Error create new reservation festive room, reservation exist : " + festiveRoom.toString());
        }
        return festiveRoom;
    }




    @RequestMapping(method = POST, value="{idPartyRoom}" )
    public FestiveRoom updateReservationFestiveRoom(@RequestParam(value="idPartyRoom") int idPartyRoom,
                                                 @RequestParam(value="event") String event,
                                                 @RequestParam(value="nbChairs") int nbChairs,
                                                 @RequestParam(value="nbTable") int nbTable,
                                                 @RequestParam(value="idClient") int idCLient){
        FestiveRoom festiveRoom = festiveRoomRepository.findByIdClient(idPartyRoom);

        if( festiveRoom != null){
            festiveRoom.setEvent(event);
            festiveRoom.setIdClient(idCLient);
            festiveRoom.setNumberChairs(nbChairs);
            festiveRoom.setNumberTables(nbTable);
            festiveRoomRepository.save(festiveRoom);
        }else{
            LOG.error ("Error update festive room, object FestiveRoom find is equal to " + festiveRoom.toString());
        }
        return null;
    }

}
