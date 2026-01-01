package de.workshops.ticketeer.ticketvendor.model;

import lombok.Getter;

@Getter
public enum EventStatus {

  ANNOUNCED("announced"),

  OPEN("open"),

  SOLD_OUT("sold out");

  private String value;

  EventStatus(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static EventStatus fromValue(String value) {
    for (EventStatus b : EventStatus.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
