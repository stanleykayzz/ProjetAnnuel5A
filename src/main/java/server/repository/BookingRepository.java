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

    /***
     * Return one object Booking by idClient passed to the function
     * @param idClient
     * @return
     */
    Booking findBookingByIdClient(int idClient);

    /***
     *  Return All object Booking by idClient passed to the function
     * @param idClient
     * @return
     */
    List<Booking> findAllByIdClient(int idClient);

    /***
     * Return all object Booking if date_end is after the param date passed
     * @param dateEnd
     * @return
     */
    List<Booking> findAllByDateEndIsAfter(Date dateEnd);

    /***
     * Return all Booking object if date_book between in two param passed
     * @param dateStart
     * @param dateEnd
     * @return
     */
    List<Booking> findAllByDateBookBetween(Date dateStart, Date dateEnd);

    /***
     *  Return all Booking  if date_start by order ASC in sql, with date passed in param
     * @param dateStart
     * @return
     */
    List<Booking> findAllByOrderByDateStartAsc(Date dateStart);
}