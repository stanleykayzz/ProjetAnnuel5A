package server.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.mail.EmailServiceImpl;
import server.model.Client;
import server.utils.ClientUtils;
import server.service.ClientService;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    public EmailServiceImpl emailService;

    /*@RequestMapping(method = RequestMethod.GET)
    public List<Client> getAll() {
        return clientService.getAll();
    }*/

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Client login(@RequestParam("email") String email, @RequestParam("password") String password) {

        int randomCode = ThreadLocalRandom.current().nextInt(0, 9999);
        emailService.sendSimpleMessage("mollard.maxime75@gmail.com",
                "Your code", String.valueOf(randomCode));
        //String pswd = ClientUtils.encryptPassword(password);
        Client client = clientService.login(email, ClientUtils.hashPassword(password));
        if (client != null) {
            client.setCode(String.valueOf(randomCode));
            clientService.updateClient(client);

            return client;
        } else {
            return null;
        }
    }


    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteClient(@RequestParam() String token) {
        /*boolean tokenAvailable = clientService.tokenAvailable(token);

        if(tokenAvailable == true){
            Client client = clientService.findByToken(token);
            clientService.deleteClient(client.getClientId());
            //Request example : http://localhost:8080/client?token=2ca5f8a5-40b6-4e16-9899-c0201c68d347
        }*/
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client) throws Exception {
        boolean clientExist = clientService.findByEmail(client.getEmail());

        if (!clientExist) {
            String pswd = ClientUtils.encryptPassword(client.getPassword());
            client.setStatus(0);
            client.setPassword(pswd);
            return clientService.addClient(client);
        } else {
            return null;
        }
    }


    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public Client updateClient(@RequestBody Client newClient, @RequestParam String token, @RequestParam String password) throws Exception {
        Client client = clientService.findByToken(token);
        String psw = ClientUtils.encryptPassword(password);
        if (client != null) {
            if (client.getPassword() == psw) {
                client.setPhone(newClient.getPhone());
                client.setCountry(newClient.getCountry());
                client.setCity(newClient.getCity());
                client.setAddress(newClient.getAddress());
                client.setPostalCode(newClient.getPostalCode());
                client.setPassword(ClientUtils.encryptPassword(newClient.getPassword()));

                clientService.updateTokenDate(client);
                clientService.updateClient(client);

                return clientService.findByToken(token);
            }
            return null;
        }
        return null;
    }


    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public boolean logout(@RequestParam String token) {
        Client client = clientService.findByToken(token);
        client.setToken(null);
        client.setTokenDate(null);
        clientService.updateClient(client);

        return true;
    }

    @RequestMapping(path = "/reloadToken", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Date reloadToken(@RequestParam String token) {
        Client client = clientService.findByToken(token);
        if (client != null) {
            clientService.updateTokenDate(client);
            clientService.updateClient(client);

            return client.getTokenDate();
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/getByToken", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Client getClientByToken(@RequestParam String token) {
        Client client = clientService.findByToken(token);

        if (client != null) {
            return clientService.findByToken(token);
        }

        return null;
    }

    @RequestMapping(path = "/confirmation", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Client confirmation(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam String code) {
        Client client = clientService.confirmation(email, ClientUtils.hashPassword(password), code);

        if (client != null) {
            clientService.generateToken(client);
            clientService.updateClient(client);

            return client;
        }

        return null;
    }
}


