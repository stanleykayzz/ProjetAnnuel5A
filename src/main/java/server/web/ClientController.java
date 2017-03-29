package server.web;

import com.sun.deploy.util.SessionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.model.Client;
import server.repository.ClientRepository;
import server.service.ClientService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    //Request example : http://localhost:8080/client/4

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client) throws Exception {
        return clientService.addClient(client);
        ////Request example : http://localhost:8080/client
        /*{
                "name": "mollard",
                "firstName": "maxime",
                "birthday": "1993-09-15",
                "email": "momo@hotmail.fr",
                "phone": "0102030405",
                "country": "france",
                "city": "Paris",
                "address": "70 rue toto",
                "postalCode": "75015",
                "password": "test"
        }*/
    }


    @RequestMapping(path = "/update",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateClient(@RequestBody Client newClient) throws Exception {
        Client client = clientService.findByEmail(newClient.getEmail());

        if(client != null) {
            client.setPhone(newClient.getPhone());
            client.setEmail(newClient.getEmail());
            client.setCountry(newClient.getCountry());
            client.setCity(newClient.getCity());
            client.setAddress(newClient.getAddress());
            client.setPostalCode(newClient.getPostalCode());
            client.setPassword(newClient.getPassword());

            clientService.updateClient(client);
        }
    }
}


