package server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.model.ServicesHotel;
import server.repository.ServicesHotelRepository;

import javax.xml.ws.Service;
import java.util.List;

/**
 * Created by ileossa on 18/06/2017.
 */
@RestController
@RequestMapping("/services")
public class ServicesHotelController {

    private ServicesHotelRepository servicesHotelRepository;


    public List<ServicesHotel> getAllServiceHotel(){
        return servicesHotelRepository.findAll();
    }


    public ServicesHotel newServiceHotel(@RequestParam(value="name")String name,
                                         @RequestParam(value = "type")String type,
                                         @RequestParam(value = "price") float price,
                                         @RequestParam(value = "comment") String comment){
        ServicesHotel newService = new ServicesHotel(name, type, price, comment);
        return servicesHotelRepository.save(newService);
    }


    // todo update
    public ServicesHotel updateServiceHotel(@RequestParam(value = "idService") int idService,
                                            @RequestParam(value="name")String name,
                                            @RequestParam(value = "type")String type,
                                            @RequestParam(value = "price") float price,
                                            @RequestParam(value = "comment") String comment){
        ServicesHotel serviceBeforeUpdate = servicesHotelRepository.findOne(idService);

        ServicesHotel newService = serviceBeforeUpdate;
        newService.setName(name);
        newService.setType(type);
        newService.setPrice(price);
        newService.setComment(comment);
        return servicesHotelRepository.save(newService);
    }
}
