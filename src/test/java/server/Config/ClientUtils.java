package server.Config;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.model.Client;
import server.repository.ClientRepository;

import java.util.Date;

/**
 * Created by ileossa on 05/07/2017.
 */
@Component
public class ClientUtils {

    @Autowired
    ClientRepository clientRepository;

    private Client riri;
    private Client fifi;
    private Client loulou;

    @Before
    public void setUp(){
        Date date_riri = new Date(100009);
        Date date_fifi = new Date(300879);
        Date date_loulou = new Date(1200879);


        riri = new Client("riri", "duck", date_riri, "riri@localhost", "0616657098", "Disney", "duck city", "12 av disney", "190183", "secretpassword", null, null);
        fifi = new Client("fifi", "duck", date_fifi, "fifi@localhost", "0665307001", "Disney", "duck city", "12 av disney", "190183", "secretpassword", null, null);
        loulou = new Client("loulou", "duck", date_loulou, "loulou@localhost", "0665302222", "Disney", "duck city", "12 av disney", "190183", "secretpassword", null, null);

        clientRepository.save(riri);
        clientRepository.save(fifi);
        clientRepository.save(loulou);
    }

    public Client getRiri(){
        return this.riri;
    }

    public Client getLoulou(){
        return loulou;
    }
}
