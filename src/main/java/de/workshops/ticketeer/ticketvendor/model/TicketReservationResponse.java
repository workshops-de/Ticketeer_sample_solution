package de.workshops.ticketeer.ticketvendor.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public class TicketReservationResponse {

  public static final String SERIALIZED_NAME_ID = "id";
  private UUID id;

  public static final String SERIALIZED_NAME_VALID_UNTIL = "validUntil";
  private OffsetDateTime validUntil;

  public static final String SERIALIZED_NAME_NUMBER = "number";
  private Integer number;

  public static final String SERIALIZED_NAME_TICKET_PRICE = "ticketPrice";
  private Price ticketPrice;

  public static final String SERIALIZED_NAME_TOTAL_PRICE = "totalPrice";
  private Price totalPrice;

  public TicketReservationResponse() {
  }

  public TicketReservationResponse id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * reservation ID
   *
   * @return id
   */
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }


  public TicketReservationResponse validUntil(OffsetDateTime validUntil) {
    this.validUntil = validUntil;
    return this;
  }

  /**
   * Get validUntil
   *
   * @return validUntil
   */
  public OffsetDateTime getValidUntil() {
    return validUntil;
  }

  public void setValidUntil(OffsetDateTime validUntil) {
    this.validUntil = validUntil;
  }


  public TicketReservationResponse number(Integer number) {
    this.number = number;
    return this;
  }

  /**
   * Get number minimum: 1
   *
   * @return number
   */
  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }


  public TicketReservationResponse ticketPrice(Price ticketPrice) {
    this.ticketPrice = ticketPrice;
    return this;
  }

  /**
   * Get ticketPrice
   *
   * @return ticketPrice
   */
  public Price getTicketPrice() {
    return ticketPrice;
  }

  public void setTicketPrice(Price ticketPrice) {
    this.ticketPrice = ticketPrice;
  }


  public TicketReservationResponse totalPrice(Price totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  /**
   * Get totalPrice
   *
   * @return totalPrice
   */
  public Price getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Price totalPrice) {
    this.totalPrice = totalPrice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TicketReservationResponse ticketReservationResponse = (TicketReservationResponse) o;
    return Objects.equals(this.id, ticketReservationResponse.id) &&
        Objects.equals(this.validUntil, ticketReservationResponse.validUntil) &&
        Objects.equals(this.number, ticketReservationResponse.number) &&
        Objects.equals(this.ticketPrice, ticketReservationResponse.ticketPrice) &&
        Objects.equals(this.totalPrice, ticketReservationResponse.totalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, validUntil, number, ticketPrice, totalPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TicketReservationResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    validUntil: ").append(toIndentedString(validUntil)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    ticketPrice: ").append(toIndentedString(ticketPrice)).append("\n");
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
