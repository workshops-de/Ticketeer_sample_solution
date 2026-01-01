package de.workshops.ticketeer.ticketvendor.model;

import lombok.Getter;

@Getter
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

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static EventCategory fromValue(String value) {
    for (EventCategory b : EventCategory.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
