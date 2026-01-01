package de.workshops.ticketeer.ticketvendor.model;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {

  /**
   * -- GETTER -- Get city
   *
   * @return city
   */
  private String city;

  public static final String SERIALIZED_NAME_POSTCODE = "postcode";
  /**
   * -- GETTER --
   *  Get postcode
   *
   * @return postcode
   */
  private String postcode;

  public static final String SERIALIZED_NAME_ADDRESS = "address";
  /**
   * -- GETTER --
   *  Get address
   *
   * @return address
   */
  private String address;

  public Address() {
  }

  public Address city(String city) {
    this.city = city;
    return this;
  }


  public Address postcode(String postcode) {
    this.postcode = postcode;
    return this;
  }

  public Address address(String address) {
    this.address = address;
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
    Address address = (Address) o;
    return Objects.equals(this.city, address.city) &&
        Objects.equals(this.postcode, address.postcode) &&
        Objects.equals(this.address, address.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, postcode, address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    postcode: ").append(toIndentedString(postcode)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
