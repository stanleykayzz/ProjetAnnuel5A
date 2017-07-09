package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import server.model.Client;
import server.model.FestiveRoom;
import server.repository.ClientRepository;
import server.repository.FestiveRoomRepository;
import server.service.ClientService;
import server.utils.ClientUtils;

import java.util.concurrent.ThreadLocalRandom;

import static server.model.Enum.AccreditationUers.ADMINISTRATEUR;

/**
 * Created by ileossa on 09/07/2017.
 */
@Service
public class DataLoaderAtLaunchApp implements ApplicationRunner{


    private FestiveRoomRepository festiveRoomRepository;
    private ClientRepository clientRepository;
    private ClientService clientService;

    @Autowired
    public DataLoaderAtLaunchApp(FestiveRoomRepository festiveRoomRepository, ClientRepository clientRepository, ClientService clientService) {
        this.festiveRoomRepository = festiveRoomRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(festiveRoomRepository.findAll().size() == 0){
            FestiveRoom festiveRoom = new FestiveRoom(1000);
            festiveRoomRepository.saveAndFlush(festiveRoom);
        }
        Client admin = clientRepository.findClientByAccreditationEquals(String.valueOf(ADMINISTRATEUR));
        if(admin == null){
            Client client = new Client();
            int randomCode = ThreadLocalRandom.current().nextInt(0, 9999);
            String pswd = ClientUtils.hashPassword(client.getPassword());
            client.setStatus(0);
            client.setCode(String.valueOf(randomCode));
            client.setPassword(pswd.toString());
            clientService.addClient(client);
        }
    }
}
