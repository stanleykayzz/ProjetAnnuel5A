package server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.model.Client;

import java.util.Date;
import java.util.List;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {

    //todo tout refaire

    @Query("select c from Client c where email = :Email and password = :Password")
    List<Client> login(@Param("Email") String email, @Param("Password") String password);

    @Query("select c from Client c where email = :Email and code = :Code")
    List<Client> confirmation(@Param("Email") String email, @Param("Code") String code);

<<<<<<< HEAD
    List<Client> findByToken(@Param("Token") String Token);

    List<Client> findByEmail(@Param("Email") String email);
=======
    boolean findOneByToken(@Param("Token") String token);

    Client findClientByEmailAndPassword(String email, String password);

<<<<<<< HEAD
>>>>>>> fixe, update, and add h2 database for dev mode, configure postgresql heroku database
=======
    Client findClientByClientIdEquals(long clientId );

    Client findClientByEmailEquals(String email);

    Client findDistinctFirstByToken(String token);
>>>>>>> Update service, add category room.
}