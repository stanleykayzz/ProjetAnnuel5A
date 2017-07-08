package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.model.FestiveRoom;
import server.repository.FestiveRoomRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ileossa on 23/05/2017.
 */
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


    @RequestMapping(method = GET, value = "/{tokenClient}")
    public FestiveRoom getReservationFestiveRoomByTokenClient(@PathVariable String  tokenClient){
        return festiveRoomRepository.findByTokenClientEquals(tokenClient);

    }

    @RequestMapping(method = GET, value="/getItems")
    public void getItems(@RequestParam(value="token") String clientToken){
        festiveRoomRepository.findByTokenClientEquals(clientToken);
    }


    @RequestMapping(method = POST )
    public FestiveRoom newReservationFestiveRoom(@RequestBody FestiveRoom festiveRoom){
        festiveRoomRepository.saveAndFlush(festiveRoom);
        return festiveRoom;
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
