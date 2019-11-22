package fr.excilys.clientapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoElementException extends RuntimeException {

    public NoElementException() {
        super("Element not found");
    }
}
