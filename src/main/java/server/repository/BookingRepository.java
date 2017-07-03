package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Booking;

import java.util.Date;
import java.util.List;

/**
 * Created by ileossa on 05/04/2017.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByTokenId(String token);
    List<Booking> findAllByDateEndIsAfter(Date dateEnd);
    List<Booking> findAllByDateBookBetween(Date dateBook);
}
