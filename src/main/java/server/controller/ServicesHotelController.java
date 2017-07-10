package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.exception.ItemNotFound;
import server.exception.TokenError;
import server.model.ServicesHotel;
import server.repository.ServicesHotelRepository;
import server.service.ClientService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ileossa on 18/06/2017.
 */
@RestController
@RequestMapping("/api/services")
public class ServicesHotelController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private ServicesHotelRepository servicesHotelRepository;
    private ClientService clientService;

    @Autowired
    public ServicesHotelController(ServicesHotelRepository servicesHotelRepository, ClientService clientService) {
        this.servicesHotelRepository = servicesHotelRepository;
        this.clientService = clientService;
    }

    public List<ServicesHotel> getAllServiceHotel(@RequestParam(value = "token")String tokenClient) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            return servicesHotelRepository.findAll();
        }
        throw new TokenError();
    }


    @RequestMapping(method = POST)
    @ResponseStatus(OK)
    public ServicesHotel newServiceHotel(@RequestParam(value="token")String tokenCLient,
                                         @RequestBody ServicesHotel newServicesHotel) throws TokenError {
        if(clientService.isAuthorized(tokenCLient)) {
            if(servicesHotelRepository.findOne(newServicesHotel.getIdServicesHotel()) == null){
                servicesHotelRepository.saveAndFlush(newServicesHotel);
            }
        }
        throw new TokenError();

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(OK)
    public ServicesHotel updateServiceHotel(@RequestParam(value = "token") String tokenClient,
                                            @RequestBody ServicesHotel servicesHotel) throws ItemNotFound, TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            if(servicesHotelRepository.findOne(servicesHotel.getIdServicesHotel()) != null){
                servicesHotelRepository.saveAndFlush(servicesHotel);
            }else {
                throw new ItemNotFound();
            }
        }
        throw new TokenError();
    }

    @RequestMapping(method = DELETE)
    @ResponseStatus(value = OK)
    public void deleteService(@RequestParam(value = "token")String tokenClient,
                              @RequestParam(value="id")int idService) throws ItemNotFound, TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            if(servicesHotelRepository.findOne(idService) != null){
                servicesHotelRepository.delete(idService);
            }else {
                throw new ItemNotFound();
            }
        }
        throw new TokenError();
    }

    @RequestMapping(method = GET)
    @ResponseStatus(value = OK)
    public List<ServicesHotel> getListServicesHotel(@RequestParam(value="token")String tokenClient) throws TokenError {
        if(clientService.isAuthorized(tokenClient)) {
            return servicesHotelRepository.findAll();
        }
        throw new TokenError();
    }
}
