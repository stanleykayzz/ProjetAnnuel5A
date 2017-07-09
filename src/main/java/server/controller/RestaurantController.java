package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.model.Restaurant;
import server.repository.ClientRepository;
import server.repository.RestaurantRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by ileossa on 22/05/2017.
 */
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @RequestMapping(method = GET, value = "/all")
    public List<Restaurant> getAllReservation(){
        return restaurantRepository.findAll();
    }



    @RequestMapping(method = GET, value="/client/{tokenClient}")
    public Restaurant getReservationByClientId(@PathVariable String tokenClient){
        return restaurantRepository.findRestaurantByTokenClientEquals(tokenClient);
    }


    @RequestMapping(method = GET, value="/restaurant/{idTable}")
    public Restaurant getRestaurantByRestaurantId(@PathVariable int idTable){
        return restaurantRepository.findRestaurantByIdTable(idTable);
    }



    @RequestMapping(method = POST, value="/book")
    @ResponseStatus(ACCEPTED)
    public void newReservation(@RequestParam(value = "token") String tokenCLient,
                                     @RequestParam(value = "type") String type,
                                     @RequestParam(value = "number") int number){

        Restaurant restaurant = new Restaurant(type, tokenCLient, number);
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
