package de.workshops.ticketeer.ticketvendor.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketOrderResponse {

  public static final String SERIALIZED_NAME_ID = "id";
  /**
   * -- GETTER -- order ID
   *
   * @return id
   */
  private UUID id;

  public static final String SERIALIZED_NAME_DATE = "date";
  /**
   * -- GETTER --
   *  order date/time
   *
   * @return date
   */
  private OffsetDateTime date;

  public static final String SERIALIZED_NAME_NUMBER = "number";
  /**
   * -- GETTER --
   *  Get number minimum: 1
   *
   * @return number
   */
  private Integer number;

  public static final String SERIALIZED_NAME_TICKET_PRICE = "ticketPrice";
  /**
   * -- GETTER --
   *  Get ticketPrice
   *
   * @return ticketPrice
   */
  private Price ticketPrice;

  public static final String SERIALIZED_NAME_TOTAL_PRICE = "totalPrice";
  /**
   * -- GETTER --
   *  Get totalPrice
   *
   * @return totalPrice
   */
  private Price totalPrice;

  public TicketOrderResponse() {
  }

  public TicketOrderResponse id(UUID id) {
    this.id = id;
    return this;
  }


  public TicketOrderResponse date(OffsetDateTime date) {
    this.date = date;
    return this;
  }


  public TicketOrderResponse number(Integer number) {
    this.number = number;
    return this;
  }


  public TicketOrderResponse ticketPrice(Price ticketPrice) {
    this.ticketPrice = ticketPrice;
    return this;
  }


  public TicketOrderResponse totalPrice(Price totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TicketOrderResponse ticketOrderResponse = (TicketOrderResponse) o;
    return Objects.equals(this.id, ticketOrderResponse.id) &&
        Objects.equals(this.date, ticketOrderResponse.date) &&
        Objects.equals(this.number, ticketOrderResponse.number) &&
        Objects.equals(this.ticketPrice, ticketOrderResponse.ticketPrice) &&
        Objects.equals(this.totalPrice, ticketOrderResponse.totalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, number, ticketPrice, totalPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TicketOrderResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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
