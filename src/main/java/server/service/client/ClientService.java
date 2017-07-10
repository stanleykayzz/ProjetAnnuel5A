package server.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import server.model.Client;
import server.model.Enum.AccreditationUers;
import server.repository.ClientRepository;

import java.util.*;

@Service
public class ClientService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private ClientRepository clientRepository;
    private SecurityClient securityClient;

    @Autowired
    public ClientService(ClientRepository clientRepository, SecurityClient securityClient) {
        this.clientRepository = clientRepository;
        this.securityClient = securityClient;
    }



    public Client findByToken(String Token) {
        List<Client> clients = clientRepository.findByToken(Token);

        if (clients.size() > 0) {
            boolean available = tokenAvailable(clients.get(0));
            if (available == true) {
                return clients.get(0);
            }
            return null;
        }
        return null;
    }

    public Client login(String email, String password) {
        List<Client> clients = clientRepository.login(email, password);

        if (clients.size() > 0) {
            return clients.get(0);
        } else {
            return null;
        }
    }

    public Client confirmation(String email, String code) {
        List<Client> clients = clientRepository.confirmation(email, code);

        if (clients.size() > 0) {
            return clients.get(0);
        } else {
            return null;
        }
    }


    public Client getById(int id) {
        return clientRepository.findOne(id);
    }

    public Client addClient(Client client) {
        LOG.trace("insert new cient in bdd: " + client.toString());
        return clientRepository.saveAndFlush(client);
    }

    public Client updateClient(Client client) {
        return clientRepository.saveAndFlush(client);
    }



    public boolean tokenAvailable(Client client) {
        Date currentDate = new Date();

        long diff = Math.abs(currentDate.getTime() - client.getTokenDate().getTime());
        long diffMinutes = diff / 60000 % 60;
        long diffHours = diff / 3600000;

        if (diffHours <= 0 && diffMinutes < 15) {
            return true;
        } else {
            return false;
        }
    }

    public boolean tokenExists(String token) {
        Client client = findByToken(token);
        if (client != null) {
            return true;
        } else {
            return false;
        }
    }



    public void updateTokenDate(Client client) {
        client.setTokenDate(new Date());
    }


    public boolean isAdministator(String tokenClient){
        Client client = clientRepository.findDistinctFirstByToken(tokenClient);
        if(tokenAvailable(client)) {
            if (client.getAccreditation().equals(AccreditationUers.ADMINISTRATEUR)) {
                return true;
            }
        }
        return false;
    }


    public Client updateNewInformationsClient(@RequestBody Client newClient, Client client, String psw) {
        if(client != null) {
            if(client.getPassword().equals(psw)){
                client.setPhone(newClient.getPhone());
                client.setCountry(newClient.getCountry());
                client.setCity(newClient.getCity());
                client.setAddress(newClient.getAddress());
                client.setPostalCode(newClient.getPostalCode());
                client.setPassword(securityClient.hashPassword(newClient.getPassword()));

                updateTokenDate(client);
                updateClient(client);

                return client;
            } else {
                throw new IllegalArgumentException("error");
            }
        } else {
            throw new IllegalArgumentException("error");
        }
    }

}
