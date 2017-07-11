package server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ileossa on 09/07/2017
 *
 * General arror if item not found client/booking/restaurant/room/....
 */
@ResponseStatus(value= HttpStatus.CONFLICT, reason="Exist")
public class ItemNotFound extends Exception {
}
