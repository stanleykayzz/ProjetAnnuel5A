package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Client;
import server.repository.ClientRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public boolean findByEmail(String Email){
        if(clientRepository.findByEmail(Email).size() > 0){
            return true;
        } else {
            return false;
        }
    }

    public Client findByToken(String Token){
        List<Client> clients = clientRepository.findByToken(Token);

        if(clients.size() > 0){
            boolean available = tokenAvailable(clients.get(0));
            if(available == true){
                return clients.get(0);
            }
            return null;
        }
        return null;
    }

    public Client login(String email, String password){
        List<Client> clients = clientRepository.login(email, password);

        if(clients.size() > 0){
            return clients.get(0);
        } else {
            return null;
        }
    }

    public void deleteClient(Long id){
        Client clients = getById(id);
        clientRepository.delete(id);
    }

    public Client getById(Long id){
        return clientRepository.findOne(id);
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    public boolean tokenAvailable(Client client){
        Date currentDate = new Date();

        long diff        = Math.abs(currentDate.getTime() - client.getTokenDate().getTime());
        long diffMinutes = diff / 60000 % 60;
        long diffHours   = diff / 3600000;

        if(diffHours <= 0 && diffMinutes < 15){
            return true;
        } else {
            return false;
        }
    }

    public boolean tokenExists(String token){
        Client client = findByToken(token);
        if(client != null){
            return true;
        } else {
            return false;
        }
    }

    public void generateToken(Client client){
        client.setToken(UUID.randomUUID().toString());
        Date dateToken = new Date();
        client.setTokenDate(dateToken);
        //boolean tokenExists = tokenExists(client.getToken());
        /*System.out.println(tokenExists);
        if(tokenExists == true) {
            this.generateToken(client);
        }*/
    }

    public void updateTokenDate(Client client){
        client.setTokenDate(new Date());
    }
}
