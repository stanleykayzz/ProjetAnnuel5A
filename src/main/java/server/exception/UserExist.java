package server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Client déjà enregistré")
public class UserExist extends RuntimeException {

    public UserExist() {

    }
}
