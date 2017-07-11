package server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.LoginException;

/**
 * Created by ileossa on 09/07/2017.
 *
 * General arror if item exist client/booking/restaurant/room/...
 */
@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Wrong token")
public class ObjectExist extends LoginException {
}
