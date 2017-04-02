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

    public Client login(String email, String password){
        List<Client> clients = clientRepository.login(email, password);

        if(clients.size() >= 0){
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
}
