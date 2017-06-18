package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.ServicesHotel;

/**
 * Created by ileossa on 18/06/2017.
 */
@Repository
public interface ServicesHotelRepository extends JpaRepository<ServicesHotel, Integer> {
}
