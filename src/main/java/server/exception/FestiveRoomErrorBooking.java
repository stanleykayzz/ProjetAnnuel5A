package server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ileossa on 08/07/2017.
 *
 * Pls use if you need return 409 statut error, if id booking exist throw this error
 */
@ResponseStatus(value= HttpStatus.CONFLICT, reason="Exist")
public class FestiveRoomErrorBooking extends RuntimeException
{
}
