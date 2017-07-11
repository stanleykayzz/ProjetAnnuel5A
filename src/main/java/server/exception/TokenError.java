package server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.LoginException;

/**
 * Created by ileossa on 09/07/2017.
 *
 *  If user send a wrong token, or check validate token. If result is false throw this sprcific execption
 */
@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Wrong token")
public class TokenError extends LoginException {
}
