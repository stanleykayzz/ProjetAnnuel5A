package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.model.Client;
import server.service.ClientService;
import server.service.mail.MailRegistrationService;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    private MailRegistrationService mailRegistrationService;

    @Autowired
    public ClientController(ClientService clientService, MailRegistrationService mailRegistrationService) {
        this.clientService = clientService;
        this.mailRegistrationService = mailRegistrationService;
    }


    @RequestMapping(path = "/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Client login(@RequestParam("email") String email, @RequestParam("password") String password){
        Client client = clientService.login(email, password);
        if(client != null){
            clientService.generateToken(client);
            clientService.updateClient(client);

            return client;
        } else {
            throw new IllegalArgumentException("Incorrect email or password");
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client newClient(@RequestBody Client client) throws Exception {
        Client newClient = clientService.addClient(client);
        mailRegistrationService.sendEmail(client, "Confirmation registration", "registration-confirmation.vm");
        return newClient;
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
            mailRegistrationService.sendEmail(client, "account updated", "account_update");
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