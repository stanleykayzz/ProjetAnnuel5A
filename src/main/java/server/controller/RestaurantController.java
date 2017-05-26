package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.model.Restaurant;
import server.repository.ClientRepository;
import server.repository.RestaurantRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ileossa on 22/05/2017.
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ClientRepository clientRepository;


    @RequestMapping(method = GET, value = "/all")
    public List<Restaurant> getAllReservation(){
        return restaurantRepository.findAll();
    }



    @RequestMapping(method = GET, value="/client/{idClient}")
    public Restaurant getReservationByClientId(@PathVariable int idClient){
        return restaurantRepository.findRestaurantByIdClient(idClient);
    }


    @RequestMapping(method = GET, value="/restaurant/{idTable}")
    public Restaurant getRestaurantByRestaurantId(@PathVariable int idTable){
        return restaurantRepository.findRestaurantByIdTable(idTable);
    }


    @RequestMapping(method = POST)
    public Restaurant newReservation(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "placeNumber")int nbPlace,
                                     @RequestParam(value = "idClient") int idClient){

        Restaurant restaurant = new Restaurant(name, nbPlace, idClient);
        return restaurantRepository.save(restaurant);
    }


    @RequestMapping(method = POST)
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
