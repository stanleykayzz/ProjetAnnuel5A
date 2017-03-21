package server.web;

import com.sun.deploy.util.SessionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.model.Client;
import server.service.ClientService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Client login(@RequestParam("email") String email,
                        @RequestParam("password") String password){
        Client client = clientService.login(email, password);
        if(null == client)
            throw new IllegalArgumentException("Incorrect email or password");
        return client;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePerson(@PathVariable("id") Long id){
        clientService.deleteClient(id);
    }

    /*
    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePerson(@RequestParam("id") Long id){
        clientService.deleteClient(id);
    }
     */
}


