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
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(method = GET, value = "/all")
    public List<Restaurant> getAllReservation(){
        return restaurantRepository.findAll();
    }



    @RequestMapping(method = GET, value="/client/{tokenClient}")
    public List<Booking> getReservationByClientId(@PathVariable String tokenClient) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            Client client = clientRepository.findClientByTokenEquals(tokenClient);
            return bookingRepository.findBookingByIdClient(client.getClientId());
        }
        throw new TokenError();
    }


    @RequestMapping(method = GET, value="/restaurant/{idTable}")
    public Restaurant getRestaurantByRestaurantId(@PathVariable int idTable){
        return restaurantRepository.findRestaurantByIdTable(idTable);
    }



    @RequestMapping(method = POST, value="/book")
    @ResponseStatus(ACCEPTED)
    public void newReservation(@RequestParam(value = "token") String tokenCLient,
                                     @RequestParam(value = "type") String service,
                                     @RequestParam(value = "number") int number) throws Exception {
        if(clientService.isAuthorized(tokenCLient)) {
            Client client  = clientRepository.findClientByTokenEquals(tokenCLient);
            //Booking book = new Booking()
            throw new Exception("not implement, try soon");
        }
        throw new TokenError();
    }


    @RequestMapping(method = POST, value="{idTable}")
    public Restaurant updateReservation(@RequestParam(value = "idTable") int idTable,
                                        @RequestParam(value = "name") String name,
                                        @RequestParam(value = "placeNumber")int nbPlace,
                                        @RequestParam(value = "idClient") int idClient){

        if(restaurantRepository.findRestaurantByIdTable(idTable) != null){
            Restaurant restaurant = restaurantRepository.findRestaurantByIdTable(idTable);

            restaurant.check(idTable);
            restaurant.check(name);
            restaurant.check(nbPlace);
            restaurant.check(idClient);

            return restaurant;
        }

        return new Restaurant();
    }




}
