package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import server.model.Client;
import server.model.Enum.AccreditationUers;
import server.model.FestiveRoom;
import server.repository.ClientRepository;
import server.repository.FestiveRoomRepository;
import server.service.ClientService;
import server.service.DateService;
import server.utils.ClientUtils;

import java.util.concurrent.ThreadLocalRandom;

import static server.model.Enum.AccreditationUers.ADMINISTRATEUR;

/**
 * Created by ileossa on 09/07/2017.
 */
@Component
public class DataLoaderAtLaunchApp implements ApplicationRunner{


    private FestiveRoomRepository festiveRoomRepository;
    private ClientRepository clientRepository;
    private ClientService clientService;
    private DateService dateService;

    @Value("${default.price.festive.room}")
    private float pricefestiveRoom;

    @Autowired
    public DataLoaderAtLaunchApp(FestiveRoomRepository festiveRoomRepository, ClientRepository clientRepository, ClientService clientService, DateService dateService) {
        this.festiveRoomRepository = festiveRoomRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
        this.dateService = dateService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        if( festiveRoomRepository.findAll().isEmpty()){
//            FestiveRoom festiveRoom = FestiveRoom.builder()
//                    .idPartyRoom(0)
//                    .price(pricefestiveRoom)
//                    .build();
//            festiveRoomRepository.save(festiveRoom);
//        }
        Client admin = clientRepository.findClientByAccreditationEquals(String.valueOf(ADMINISTRATEUR));
        if(admin == null){
            Client client = Client.builder()
                    .clientId(0)
                    .address("root")
                    .accreditation(AccreditationUers.ADMINISTRATEUR.toString())
                    .birthday(dateService.stringToDate("2000-02-01"))
                    .city("root")
                    .country("root")
                    .postalCode("root")
                    .email("root@root.com")
                    .firstName("root")
                    .name("root")
                    .password("Asx$ijrT-45")
                    .phone("000000000")
                    .build();
            int randomCode = ThreadLocalRandom.current().nextInt(0, 9999);
            String pswd = ClientUtils.hashPassword(client.getPassword());
            client.setStatus(0);
            client.setCode(String.valueOf(randomCode));
            client.setPassword(pswd.toString());
            clientService.addClient(client);
        }
    }
}
