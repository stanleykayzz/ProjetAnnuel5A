package server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ileossa on 11/07/2017.
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Cannot confirm booking")
public class InternalError extends Exception  {
}
