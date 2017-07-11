package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.exception.TokenError;
import server.model.CategoryRoom;
import server.repository.CategoryRoomRepository;
import server.service.client.ClientService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ileossa on 09/07/2017.
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryRoomRepository categoryRoomRepository;
    private ClientService clientService;

    @Autowired
    public CategoryController(CategoryRoomRepository categoryRoomRepository, ClientService clientService) {
        this.categoryRoomRepository = categoryRoomRepository;
        this.clientService = clientService;
    }


    @RequestMapping(method = POST)
    @ResponseStatus(OK)
    public void newCategory(@RequestParam(value="token")String tokenClient,
                            @RequestBody CategoryRoom categoryRoom) throws TokenError {
        if(clientService.isAdministator(tokenClient)){
            categoryRoomRepository.saveAndFlush(categoryRoom);
        }
        throw new TokenError();
    }


    @RequestMapping(method = DELETE)
    @ResponseStatus(OK)
    public void deleteCategory(@RequestParam(value = "token")String tokenClient,
                               @RequestParam(value = "id") int idCategory) throws TokenError {
        if(clientService.isAdministator(tokenClient)){
            categoryRoomRepository.delete(idCategory);
        }
        throw new TokenError();
    }


    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    public List<CategoryRoom> getAll() {
        return categoryRoomRepository.findAll();
    }
}
