package server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by molla on 22/06/2017.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Argument Exception")
public class ArgumentException extends RuntimeException {
}
