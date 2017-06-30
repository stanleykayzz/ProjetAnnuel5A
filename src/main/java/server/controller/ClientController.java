package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.exception.BadCode;
import server.exception.BadPassword;
import server.exception.UserExist;
import server.exception.UserNotFound;

import server.model.Client;
import server.utils.ClientUtils;
import server.service.ClientService;
import server.service.mail.MailRegistrationService;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@CrossOrigin(origins = "http://localhost:63342")
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
    public Client login(@RequestParam("email") String email, @RequestParam("password") String password) throws UserNotFound {
        String pswd = ClientUtils.hashPassword(password);
        Client client = clientService.login(email, pswd);

        if(client != null){
            clientService.generateToken(client);
            clientService.updateClient(client);

            return client;
        } else {
            throw new UserNotFound();
        }
    }

<<<<<<< HEAD
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
    public Client addClient(@RequestBody Client client) throws UserExist {
        boolean clientExist = clientService.findByEmail(client.getEmail());
=======

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client newClient(@RequestBody Client client) throws Exception {
        Client newClient = clientService.addClient(client);
        mailRegistrationService.sendEmail(client, "Confirmation registration", "registration-confirmation.vm");
        return newClient;
    }
>>>>>>> integratation mailservice in controller

        if(!clientExist){
            int randomCode = ThreadLocalRandom.current().nextInt(0, 9999);

            String pswd = ClientUtils.hashPassword(client.getPassword());
            client.setStatus(0);
            client.setCode(String.valueOf(randomCode));
            client.setPassword(pswd.toString());
            return clientService.addClient(client);
        } else {
            throw new UserExist();
        }
    }

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
<<<<<<< HEAD
    public Client updateClient(@RequestBody Client newClient, @RequestParam String token, @RequestParam String password) throws UserNotFound, BadPassword {
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
                throw new BadPassword();
            }
        } else {
            throw new UserNotFound();
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
=======
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
>>>>>>> integratation mailservice in controller
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/getByToken", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
<<<<<<< HEAD
    public Client getClientByToken(@RequestParam String token){
        Client client = clientService.findByToken(token);

        if(client != null){
            return clientService.findByToken(token);
        }

        return null;
    }
=======
    public void logout(@RequestParam String token){
        boolean tokenExists = clientService.tokenExists(token);
        if(tokenExists == true){
            Client client = clientService.findByToken(token);
            client.setToken(null);
            client.setTokenDate(null);
>>>>>>> integratation mailservice in controller

    @RequestMapping(path = "/confirmation", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Client confirmation(@RequestParam("email") String email, @RequestParam String code) throws BadCode {
        Client client = clientService.confirmation(email, code);

        if (client != null) {
            clientService.generateToken(client);
            client.setCode("OK");
            clientService.updateClient(client);

            return client;
        } else {
            throw new BadCode();
        }
    }
<<<<<<< HEAD

    @RequestMapping(path = "/passwordRecovery", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void passwordRecovery(@RequestParam("email") String email) throws BadCode {
        boolean clientExist = clientService.findByEmail(email);

        if(clientExist)
            clientService.passwordRecovery(email);

    }
}


=======
}
>>>>>>> integratation mailservice in controller
