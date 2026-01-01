package de.workshops.ticketeer.ticketvendor.model;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketOrderRequest {

  public static final String SERIALIZED_NAME_CATEGORY = "category";
  /**
   * -- GETTER -- Get category
   *
   * @return category
   */
  private String category;

  public static final String SERIALIZED_NAME_NUMBER = "number";
  /**
   * -- GETTER --
   *  Get number minimum: 1
   *
   * @return number
   */
  private Integer number;

  public TicketOrderRequest() {
  }

  public TicketOrderRequest category(String category) {
    this.category = category;
    return this;
  }


  public TicketOrderRequest number(Integer number) {
    this.number = number;
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
    TicketOrderRequest ticketOrderRequest = (TicketOrderRequest) o;
    return Objects.equals(this.category, ticketOrderRequest.category) &&
        Objects.equals(this.number, ticketOrderRequest.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, number);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TicketOrderRequest {\n");
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
