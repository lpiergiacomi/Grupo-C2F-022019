package unq.tpi.desapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ElementNotFoundException(String message) {
        super(message);
    }
}
