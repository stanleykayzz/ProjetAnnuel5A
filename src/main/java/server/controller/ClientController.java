package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.model.Client;
import server.service.ClientService;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /*@RequestMapping(method = RequestMethod.GET)
    public List<Client> getAll() {
        return clientService.getAll();
    }*/

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Client login(@RequestParam("email") String email, @RequestParam("password") String password){
        Client client = clientService.login(email, password);
        if(client != null){
            clientService.generateToken(client);
            clientService.updateClient(client);

            return client;
            //Request example : http://localhost:8080/client/login?email=b&password=a
        } else {
            throw new IllegalArgumentException("Incorrect email or password");
        }
    }


    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteClient(@RequestParam() String token){
        boolean tokenAvailable = clientService.tokenAvailable(token);

        if(tokenAvailable == true){
            Client client = clientService.findByToken(token);
            clientService.deleteClient(client.getClientId());
            //Request example : http://localhost:8080/client?token=2ca5f8a5-40b6-4e16-9899-c0201c68d347
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client) throws Exception {
        return clientService.addClient(client);
        ////Request example : http://localhost:8080/client
        /*{
            "name": "mollard",
            "firstName": "john",
            "birthday": "1993-09-16",
            "email": "momo7450@hotmail.fr",
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
    public boolean updateClient(@RequestBody Client newClient, @RequestParam String token) throws Exception {


        boolean tokenAvailable = clientService.tokenAvailable(token);

        if( tokenAvailable == true) {
            Client client = clientService.findByToken(token);

            client.setPhone(newClient.getPhone());
            client.setCountry(newClient.getCountry());
            client.setCity(newClient.getCity());
            client.setAddress(newClient.getAddress());
            client.setPostalCode(newClient.getPostalCode());
            client.setPassword(newClient.getPassword());


            clientService.updateTokenDate(client);
            clientService.updateClient(client);

            return true;
        } else {
            return false;
        }
    }


    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void logout(@RequestParam String token){

        boolean tokenExists = clientService.tokenExists(token);

        if(tokenExists == true){
            Client client = clientService.findByToken(token);
            client.setToken(null);
            client.setTokenDate(null);

            clientService.updateClient(client);
        }
    }
}


