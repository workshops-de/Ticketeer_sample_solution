package de.workshops.ticketeer.ticketvendor.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum EventCategory {

  MUSIC("music"),

  CIRCUS("circus"),

  THEATER("theater"),

  OPERA("opera"),

  SPORT("sport"),

  OTHER("other");

  private final String value;

  EventCategory(String value) {
    this.value = value;
  }
}
