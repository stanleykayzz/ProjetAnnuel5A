package server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.model.Client;

import java.util.List;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {

    @Query("select c from Client c where email = :Email and password = :Password")
    List<Client> login(@Param("Email") String email, @Param("Password") String password);

    List<Client> findByEmail(@Param("Email") String mail);
}
