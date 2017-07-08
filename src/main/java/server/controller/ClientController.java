package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.model.Client;
import server.repository.ClientRepository;
import server.service.ClientService;
import server.service.mail.MailService;
import server.utils.ClientUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private ClientService clientService;

    private MailService mailService;

    @Autowired
    public ClientController(ClientService clientService, MailService mailService) {
        this.clientService = clientService;
        this.mailService = mailService;
    }


    @RequestMapping(path = "/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Client login(@RequestParam("email") String email, @RequestParam("password") String password) {
        String pswd = ClientUtils.hashPassword(password);
        Client client = clientService.login(email, pswd);

        if(client != null){
            clientService.generateToken(client);
            clientService.updateClient(client);

            return client;
        } else {
            throw new IllegalArgumentException("error");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteClient(@RequestParam() String token){
        /*boolean tokenAvailable = clientService.tokenAvailable(token);

        if(tokenAvailable == true){
            Client client = clientService.findByToken(token);
            clientService.deleteClient(client.getClientId());
            //Request example : http://localhost:8080/client?token=2ca5f8a5-40b6-4e16-9899-c0201c68d347
        }*/
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client) {
        boolean clientExist = clientService.findByEmail(client.getEmail());

        if(!clientExist){
            int randomCode = ThreadLocalRandom.current().nextInt(0, 9999);

            String pswd = ClientUtils.hashPassword(client.getPassword());
            client.setStatus(0);
            client.setCode(String.valueOf(randomCode));
            client.setPassword(pswd.toString());
            return clientService.addClient(client);
        } else {
            throw new IllegalArgumentException("error");
        }
    }

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public Client updateClient(@RequestBody Client newClient, @RequestParam String token, @RequestParam String password) {
        Client client = clientService.findByToken(token);
        String psw = ClientUtils.hashPassword(password);

        if(client != null) {
            if(client.getPassword().equals(psw)){
                client.setPhone(newClient.getPhone());
                client.setCountry(newClient.getCountry());
                client.setCity(newClient.getCity());
                client.setAddress(newClient.getAddress());
                client.setPostalCode(newClient.getPostalCode());
                client.setPassword(ClientUtils.hashPassword(newClient.getPassword()));

                clientService.updateTokenDate(client);
                clientService.updateClient(client);

                return client;
            } else {
                throw new IllegalArgumentException("error");
            }
        } else {
            throw new IllegalArgumentException("error");
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public boolean logout(@RequestParam String token){
        Client client = clientService.findByToken(token);
        client.setToken(null);
        client.setTokenDate(null);
        clientService.updateClient(client);

        return true;
    }

    @RequestMapping(path = "/reloadToken", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Date reloadToken(@RequestParam String token){
        Client client = clientService.findByToken(token);
        if(client != null){
            clientService.updateTokenDate(client);
            clientService.updateClient(client);

            return client.getTokenDate();
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/getByToken", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Client getClientByToken(@RequestParam String token){
        Client client = clientService.findByToken(token);

        if(client != null){
            return clientService.findByToken(token);
        }

        return null;
    }

    @RequestMapping(path = "/confirmation", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Client confirmation(@RequestParam("email") String email, @RequestParam String code) {
        Client client = clientService.confirmation(email, code);

        if (client != null) {
            clientService.generateToken(client);
            client.setCode("OK");
            clientService.updateClient(client);

            return client;
        } else {
            throw new IllegalArgumentException("error");
        }
    }

    @RequestMapping(path = "/passwordRecovery", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void passwordRecovery(@RequestParam("email") String email) {
        boolean clientExist = clientService.findByEmail(email);

        if(clientExist)
            clientService.passwordRecovery(email);

    }
}