package de.workshops.ticketeer.ticketvendor;

public class TicketException extends RuntimeException {

  private final String reason;
  private final String detail;

  public TicketException(String reason, String detail) {
    this.reason = reason;
    this.detail = detail;
  }

  public String getReason() {
    return reason;
  }

  public String getDetail() {
    return detail;
  }
}
