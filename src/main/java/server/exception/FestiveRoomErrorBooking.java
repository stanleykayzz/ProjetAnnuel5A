package server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ileossa on 08/07/2017.
 */
@ResponseStatus(value= HttpStatus.CONFLICT, reason="Exist")
public class FestiveRoomErrorBooking extends RuntimeException
{
}