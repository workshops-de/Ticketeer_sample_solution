package de.workshops.ticketeer.ticketvendor.model;

import java.util.Objects;

public class TicketReservationRequest {

  public static final String SERIALIZED_NAME_CATEGORY = "category";
  private String category;

  public static final String SERIALIZED_NAME_NUMBER = "number";
  private Integer number;

  public TicketReservationRequest() {
  }

  public TicketReservationRequest category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   *
   * @return category
   */
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }


  public TicketReservationRequest number(Integer number) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TicketReservationRequest ticketReservationRequest = (TicketReservationRequest) o;
    return Objects.equals(this.category, ticketReservationRequest.category) &&
        Objects.equals(this.number, ticketReservationRequest.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, number);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TicketReservationRequest {\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
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
