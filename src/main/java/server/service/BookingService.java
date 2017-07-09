package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Booking;
import server.model.Enum.Statut;

/**
 * Created by ileossa on 03/07/2017.
 */
@Service
public class BookingService {

    private DateService dateService;

    @Autowired
    public BookingService(DateService dateService) {
        this.dateService = dateService;
    }

    public boolean isFree(Booking book) {
        long curentTT = dateService.currentLocalTime().getTime();
        if(((book.getDateStart().getTime() - curentTT) == 0 )&&(((book.getDateEnd().getTime() - curentTT) == 0))) {
            return true;
        }
        return false;
    }

    public boolean isVacant(Booking booking){
        if(booking.getStatut() == Statut.VACANT){
            return true;
        }else{
            return false;
        }
    }
}
