package server.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import server.Application;
import server.model.Client;
import server.repository.ClientRepository;

import java.util.Date;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by ileossa on 01/07/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({"spring.profiles.active: test"})
public class ClientTest {


    private ClientRepository clientRepository;

    @Autowired
    public ClientTest(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Before
    public void setUp(){
        Date date_riri = new Date(100009);
        Date date_fifi = new Date(300879);
        Date date_loulou = new Date(1200879);


        Client riri = new Client("riri", "duck", date_riri, "riri@localhost", "0616657098", "Disney", "duck city", "12 av disney", "190183", "secretpassword", null, null);
        Client fifi = new Client("fifi", "duck", date_fifi, "fifi@localhost", "0665307001", "Disney", "duck city", "12 av disney", "190183", "secretpassword", null, null);
        Client loulou = new Client("loulou", "duck", date_loulou, "loulou@localhost", "0665302222", "Disney", "duck city", "12 av disney", "190183", "secretpassword", null, null);

        clientRepository.save(riri);
        clientRepository.save(fifi);
        clientRepository.save(loulou);
    }


/*
    {
        name       : "name",
        firstName  : "firstname",
        sexe       : "sexe",
        birthday   : "birthday",
        email      : "email",
        phone      : "phone",
        country    : "country",
        city       : "city",
        address    : "address",
        postalCode : "postalcode",
        password   : "password_1"
    }
*/

    @Test
    public void should_create_client_donald(){
        // todo refaire en se basant sur le json fournis par maxime
        given().param("name","kevin").param("password","hello123").param("email","kevin.vivor@gmail.com")
                .when().post("/user").then().log().all()
                .body("id",is(4))
                .body("password",is("hello123"))
                .body("name", is("kevin"))
                .body("email",is("kevin.vivor@gmail.com"));
    }


    @Test
    public void should_get_user_riri(){
        given()
                .when().get("/user/2").then().log().all()
                .body("id",is(2))
                .body("password",is("sam"))
                .body("name", is("saman"))
                .body("email",is("sam@gmail.com")) ;
    }


    @Test
    public void should_login_riri(){}


    @Test
    public void should_logout_riri(){}


    @Test
    public void should_update_client(){}




}
