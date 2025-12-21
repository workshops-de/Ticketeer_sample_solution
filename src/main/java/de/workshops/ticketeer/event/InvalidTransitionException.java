package de.workshops.ticketeer.event;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidTransitionException extends RuntimeException {
    public InvalidTransitionException(String message) {
        super(message);
    }

    public InvalidTransitionException(String message, Throwable cause) {
        super(message, cause);
    }
}