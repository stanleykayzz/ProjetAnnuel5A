package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Booking;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

/**
 * Created by ileossa on 05/04/2017.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findBookingById(int idClient);
    List<Booking> findAllById(int idClient);
    List<Booking> findAllByDateEndIsAfter(Date dateEnd);
    List<Booking> findAllByDateBookBetween(Date dateStart, Date dateEnd);
    List<Booking> findAllByDateStartAfterAndDateEndAfter(Date dateStart, Date dateEnd);
    List<Booking> findAllByOrderByDateEndAsc();

}
