package server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Client non trouvé")
public class BadPassword extends RuntimeException {
    public BadPassword(){

    }
}
