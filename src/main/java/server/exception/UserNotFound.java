package server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Client non trouvé")
public class UserNotFound extends RuntimeException  {

    public UserNotFound() {

    }
}
