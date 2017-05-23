package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.repository.ClientRepository;
import server.repository.RoomRepository;

/**
 * Created by ileossa on 23/05/2017.
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ClientRepository clientRepository;

    //todo renvoie la list de tout les room


    //todo renvoie la room avec roomId


    // todo renvoie la room avec userId


    //todo new room


    //update room
}
