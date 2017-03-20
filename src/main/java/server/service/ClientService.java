package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Client;
import server.repository.ClientRepository;

import java.util.List;

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

    public Client findByEmail(String Email){
        List<Client> clients = clientRepository.findByEmail(Email);
        return (clients.size() == 0) ? null : clients.get(0);
    }

    /*public Client getById(Long id){
        return clientRepository.findOne(id);
    }

    public Client login(String pseudo, String password){
        List<Client> clients = clientRepository.login(pseudo, password);
        return (clients.size() == 0) ? null : clients.get(0);
    }

    public void deleteClient(Long id){
        Client clients = getById(id);
        clientRepository.delete(id);
    }*/
}
