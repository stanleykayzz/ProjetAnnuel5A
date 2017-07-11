package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import server.model.Client;
import server.model.vluesUtils.AccreditationUers;
import server.model.FestiveRoom;
import server.repository.ClientRepository;
import server.repository.FestiveRoomRepository;
import server.service.client.ClientService;
import server.service.DateService;
import server.service.client.SecurityClient;
import server.service.mail.MailService;

import static server.model.vluesUtils.AccreditationUers.ADMINISTRATEUR;

/**
 * Created by ileossa on 09/07/2017.
 */

/**
 * At launch application this class and this code is execute, with run() methode
 */
@Component
public class DataLoaderAtLaunchApp implements ApplicationRunner{


    private FestiveRoomRepository festiveRoomRepository;
    private ClientRepository clientRepository;
    private ClientService clientService;
    private DateService dateService;
    private SecurityClient securityClient;
    private MailService mailService;

    @Value("${default.price.festive.room}")
    private float pricefestiveRoom;

    @Autowired
    public DataLoaderAtLaunchApp(FestiveRoomRepository festiveRoomRepository, ClientRepository clientRepository, ClientService clientService, DateService dateService, SecurityClient securityClient, MailService mailService) {
        this.festiveRoomRepository = festiveRoomRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
        this.dateService = dateService;
        this.securityClient = securityClient;
        this.mailService = mailService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create the festive room (1 element)
        if( festiveRoomRepository.findAll().isEmpty()){
            FestiveRoom festiveRoom = FestiveRoom.builder()
                    .id(0)
                    .price(pricefestiveRoom)
                    .build();
            festiveRoomRepository.save(festiveRoom);
        }
        // Create Admin user, to control and acces panel Administrator
        Client admin = clientRepository.findClientByAccreditationEquals(String.valueOf(ADMINISTRATEUR));
        if(admin == null){
            Client client = Client.builder()
                    .id(0)
                    .address("root")
                    .accreditation(AccreditationUers.ADMINISTRATEUR.toString())
                    .birthday(dateService.stringToDate("2000-02-01"))
                    .city("root")
                    .country("root")
                    .postalCode("root")
                    .email("root@root.com")
                    .firstName("root")
                    .name("root")
                    .password("Asx$ijrT-45")  // default password
                    .phone("000000000")
                    .build();
            client = securityClient.createAndUpdatePasswordClient(client);
            clientService.addClient(client);
        }
    }
}
