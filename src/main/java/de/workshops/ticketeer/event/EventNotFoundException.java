package de.workshops.ticketeer.event;

import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class EventNotFoundException extends NoSuchElementException {

  EventNotFoundException() {
    super();

    log.error("Event not found");
  }
}