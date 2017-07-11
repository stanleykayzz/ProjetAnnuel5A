package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.exception.ObjectExist;
import server.exception.TokenError;
import server.model.Client;
import server.model.Enum.ClientStatus;
import server.repository.ClientRepository;
import server.service.client.ClientService;
import server.service.mail.MailService;
import server.service.client.SecurityClient;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private ClientService clientService;
    private ClientRepository clientRepository;
    private MailService mailService;
    private SecurityClient securityClient;

    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository, MailService mailService, SecurityClient securityClient) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.mailService = mailService;
        this.securityClient = securityClient;
    }


    @RequestMapping(path = "/login", method = GET)
    @ResponseStatus(value = OK)
    public Client login(@RequestParam("email") String email, @RequestParam("password") String password) {
        String pswd = securityClient.hashPassword(password);
        Client client = clientService.login(email, pswd);

        if(client != null){
            clientService.updateClient(client);
            return client;
        } else {
            throw new IllegalArgumentException("error");
        }
    }


    @RequestMapping(path = "/logout", method = GET)
    @ResponseStatus(value = OK)
    public boolean logout(@RequestParam String token){
        Client client = clientService.findByToken(token);
        client.setToken(null);
        client.setTokenDate(null);
        clientService.updateClient(client);

        return true;
    }


    @RequestMapping(method = GET, value="/adminGetList")
    @ResponseStatus(OK)
    public List<Client> getLIstIsAdmin(@RequestParam(value = "token") String tokenClient) throws TokenError {
        if(clientService.isAdministator(tokenClient)){
            return clientRepository.findAll();
        }
        throw new TokenError();
    }


    @RequestMapping(path = "/reloadToken", method = GET)
    @ResponseStatus(value = OK)
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

    @RequestMapping(path = "/getByToken", method = GET)
    @ResponseStatus(value = OK)
    public Client getClientByToken(@RequestParam String token){
        Client client = clientService.findByToken(token);

        if(client != null){
            return clientService.findByToken(token);
        }

        return null;
    }

    @RequestMapping(path = "/confirmation", method = GET)
    @ResponseStatus(value = OK)
    public Client confirmation(@RequestParam("email") String email, @RequestParam String code) {
        Client client = clientService.confirmation(email, code);

        if (client != null) {
            client.setCode("OK");
            clientService.updateClient(client);

            return client;
        } else {
            throw new IllegalArgumentException("error");
        }
    }

   @RequestMapping(value = "/recovery", method = GET)
    @ResponseStatus(OK)
    public void recoveryPasswordClient(@RequestParam(value = "email") String email){
        Client client = clientRepository.findClientByEmailEquals(email);
        if(client != null) {
            client = securityClient.createAndUpdatePasswordClient(client);
            mailService.sendEmail(client, "Reset password", "account_update.vm");
        }
    }



    @RequestMapping(method = POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client) throws ObjectExist {

        Client clientExist = clientRepository.findClientByEmailEquals(client.getEmail());
        if (clientExist == null) {
            securityClient.createAndUpdatePasswordClient(client);
            mailService.sendEmail(client, "Confirmation registration", "registration-confirmation.vm");
            return clientService.addClient(client);
        } else {
            throw new ObjectExist();
        }
    }



    @RequestMapping(path = "/update",method = POST)
    @ResponseStatus(value = OK)
    public Client updateClient(@RequestBody Client newClient, @RequestParam String token, @RequestParam String password) {
        Client client = clientService.findByToken(token);
        String psw = securityClient.hashPassword(password);
        mailService.sendEmail(client, "Update account", "account_update.vm");
        return clientService.updateNewInformationsClient(newClient, client, psw);
    }



    @RequestMapping(method = DELETE)
    @ResponseStatus(value = ACCEPTED)
    public String deleteClient(@RequestParam() String token){
        Client client = clientRepository.findClientByTokenEquals(token);
        client.setClientStatus(ClientStatus.DESACTIVATE);
        clientRepository.saveAndFlush(client);
        return "redirect:index.html";
    }


}
