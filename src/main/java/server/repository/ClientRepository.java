package server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.model.Client;

import java.util.List;

/***
 *  It's differents approchate, tou can use full hibernat with method[champ][laison][second_champ]
 */
@Repository
public interface ClientRepository  extends JpaRepository<Client, Integer> {



    @Query("select c from Client c where email = :Email and password = :Password")
    List<Client> login(@Param("Email") String email, @Param("Password") String password);

    @Query("select c from Client c where email = :Email and code = :Code")
    List<Client> confirmation(@Param("Email") String email, @Param("Code") String code);

    List<Client> findByToken(@Param("Token") String token);

    /***
     * Return client object if id  == with param passed
     * @param clientId
     * @return
     */
    Client findClientById(long clientId );

    /**
     * Find and return client object if name equals with param passed
     * @param email
     * @return
     */
    Client findClientByEmailEquals(String email);

    /**
     * Find and return only specified object client when token equals param
     * @param token
     * @return
     */
    Client findDistinctFirstByToken(String token);

    /***
     * find and retourn client if Accreditation enum equals param
     * @param accreditation
     * @return
     */
    Client findClientByAccreditationEquals(String accreditation);

    /***
     * Find and retourn client if token equals param passed
     * @param tokenClient
     * @return
     */
    Client findClientByTokenEquals(String tokenClient);
}