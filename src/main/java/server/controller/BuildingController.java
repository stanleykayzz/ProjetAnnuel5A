package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.exception.TokenError;
import server.model.Building;
import server.repository.BuildingRepository;
import server.service.client.ClientService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by ileossa on 10/07/2017.
 */
@RestController
@RequestMapping("/api/building")
public class BuildingController {

    private BuildingRepository buildingRepository;
    private ClientService clientService;

    @Autowired
    public BuildingController(BuildingRepository buildingRepository, ClientService clientService) {
        this.buildingRepository = buildingRepository;
        this.clientService = clientService;
    }

    @RequestMapping(method = PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateBuilding(@RequestParam(value = "token") String token,
                               @RequestBody Building building) throws TokenError {
        if(clientService.isAdministator(token)){
            if(buildingRepository.findOne(building.getId()) != null){
                buildingRepository.saveAndFlush(building);
            }
        }throw new TokenError();
    }


    @RequestMapping(method = DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBuilding(@RequestParam(value = "token") String tokenClient,
                               @RequestParam(value = "id") int id) throws TokenError {
        if(clientService.isAdministator(tokenClient)){
            if(buildingRepository.findOne(id) != null){
                buildingRepository.delete(id);
            }
        }throw new TokenError();
    }


    @RequestMapping(method = GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Building> getListBuilding(){
        return buildingRepository.findAll();
    }


     @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createBuilding(@RequestParam(value = "token") String tokrnClient,
                               @RequestBody Building building) throws TokenError {
         if(clientService.isAdministator(tokrnClient)){
             buildingRepository.saveAndFlush(building);
         }throw new TokenError();
     }
}
