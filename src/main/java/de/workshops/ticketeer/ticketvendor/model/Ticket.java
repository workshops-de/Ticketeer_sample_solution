package de.workshops.ticketeer.ticketvendor.model;

import java.util.Objects;
import lombok.Setter;

@Setter
public class Ticket {

  public static final String SERIALIZED_NAME_TOTAL_NUMBER = "totalNumber";
  private Integer totalNumber;

  public static final String SERIALIZED_NAME_RESERVED = "reserved";
  private Integer reserved;

  public static final String SERIALIZED_NAME_ORDERED = "ordered";
  private Integer ordered;

  public static final String SERIALIZED_NAME_CATEGORY = "category";
  private String category;

  public static final String SERIALIZED_NAME_PRICE = "price";
  private Price price;

  public Ticket() {
  }

  public Ticket totalNumber(Integer totalNumber) {
    this.totalNumber = totalNumber;
    return this;
  }

  /**
   * Total number of event tickets for a certain category.
   *
   * @return totalNumber
   */
  public Integer getTotalNumber() {
    return totalNumber;
  }


  public Ticket reserved(Integer reserved) {
    this.reserved = reserved;
    return this;
  }

  /**
   * Number of reserved event tickets for a certain category.
   *
   * @return reserved
   */
  public Integer getReserved() {
    return reserved;
  }


  public Ticket ordered(Integer ordered) {
    this.ordered = ordered;
    return this;
  }

  /**
   * Number of ordered event tickets for a certain category.
   *
   * @return ordered
   */
  public Integer getOrdered() {
    return ordered;
  }


  public Ticket category(String category) {
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


  public Ticket price(Price price) {
    this.price = price;
    return this;
  }

  /**
   * Ticket price in this category
   *
   * @return price
   */
  public Price getPrice() {
    return price;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ticket ticket = (Ticket) o;
    return Objects.equals(this.totalNumber, ticket.totalNumber) &&
        Objects.equals(this.reserved, ticket.reserved) &&
        Objects.equals(this.ordered, ticket.ordered) &&
        Objects.equals(this.category, ticket.category) &&
        Objects.equals(this.price, ticket.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalNumber, reserved, ordered, category, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ticket {\n");
    sb.append("    totalNumber: ").append(toIndentedString(totalNumber)).append("\n");
    sb.append("    reserved: ").append(toIndentedString(reserved)).append("\n");
    sb.append("    ordered: ").append(toIndentedString(ordered)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
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
