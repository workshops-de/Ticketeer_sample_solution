package de.workshops.ticketeer.ticketvendor.model;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.Setter;

@Setter
public class Price {

  public static final String SERIALIZED_NAME_AMOUNT = "amount";
  private BigDecimal amount;

  public static final String SERIALIZED_NAME_CURRENCY = "currency";
  private String currency;

  public Price() {
  }

  public Price amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   *
   * @return amount
   */
  public BigDecimal getAmount() {
    return amount;
  }


  public Price currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   *
   * @return currency
   */
  public String getCurrency() {
    return currency;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Price price = (Price) o;
    return Objects.equals(this.amount, price.amount) &&
        Objects.equals(this.currency, price.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Price {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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
