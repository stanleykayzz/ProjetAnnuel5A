package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.exception.TokenError;
import server.model.Booking;
import server.model.Client;
import server.model.Restaurant;
import server.repository.BookingRepository;
import server.repository.ClientRepository;
import server.repository.RestaurantRepository;
import server.service.ClientService;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by ileossa on 22/05/2017.
 */
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private RestaurantRepository restaurantRepository;
    private ClientService clientService;
    private BookingRepository bookingRepository;
    private ClientRepository clientRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository, ClientService clientService, BookingRepository bookingRepository) {
        this.restaurantRepository = restaurantRepository;
        this.clientService = clientService;
        this.bookingRepository = bookingRepository;
    }

    @RequestMapping(method = GET)
    public List<Restaurant> getAllReservation(){
        return restaurantRepository.findAll();
    }


    @RequestMapping(method = GET, value="/client/{tokenClient}")
    public List<Booking> getReservationByClientId(@PathVariable String tokenClient) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            Client client = clientRepository.findClientByTokenEquals(tokenClient);
            return bookingRepository.findAllByIdClient(client.getClientId());
        }
        throw new TokenError();
    }


    @RequestMapping(method = GET, value="/restaurant/{idTable}")
    public Restaurant getRestaurantByRestaurantId(@PathVariable int idTable){
        return restaurantRepository.findRestaurantByIdTable(idTable);
    }



    @RequestMapping(method = POST, value="/book")
    @ResponseStatus(ACCEPTED)
    public void create(@RequestParam(value = "token") String tokenCLient,
                       @RequestBody Restaurant restaurant) throws Exception {
        if(clientService.isAuthorized(tokenCLient)) {
            restaurantRepository.save(restaurant);
        }
        throw new TokenError();
    }


    @RequestMapping(method = PUT)
    @ResponseStatus(ACCEPTED)
    public void update(@RequestParam(value="token")String tokenClient,
                       @RequestBody Restaurant restaurant) throws TokenError {
        if(clientService.isAuthorized(tokenClient)){
            if(restaurantRepository.findOne(restaurant.getIdTable()) != null){
                restaurantRepository.saveAndFlush(restaurant);
            }
        }
        throw new TokenError();
    }


    @RequestMapping(method = DELETE)
    @ResponseStatus(ACCEPTED)
    public void delete(@RequestParam(value = "token")String tokenClient,
                       @RequestParam(value="id") int id) throws TokenError {
        if(clientService.isAuthorized(tokenClient)){
            if(restaurantRepository.findOne(id) != null){
                restaurantRepository.delete(id);
            }
        }
        throw new TokenError();
    }





}
