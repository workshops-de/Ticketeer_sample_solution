package de.workshops.ticketeer.ticketvendor.model;

import java.util.Objects;
import lombok.Setter;

@Setter
public class Location {

  public static final String SERIALIZED_NAME_NAME = "name";
  private String name;

  public static final String SERIALIZED_NAME_ADDRESS = "address";
  private Address address;

  public static final String SERIALIZED_NAME_GEOLOCATION = "geolocation";
  private Geolocation geolocation;

  public Location() {
  }

  public Location name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   */
  public String getName() {
    return name;
  }


  public Location address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   *
   * @return address
   */
  public Address getAddress() {
    return address;
  }


  public Location geolocation(Geolocation geolocation) {
    this.geolocation = geolocation;
    return this;
  }

  /**
   * Get geolocation
   *
   * @return geolocation
   */
  public Geolocation getGeolocation() {
    return geolocation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location = (Location) o;
    return Objects.equals(this.name, location.name) &&
        Objects.equals(this.address, location.address) &&
        Objects.equals(this.geolocation, location.geolocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address, geolocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Location {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    geolocation: ").append(toIndentedString(geolocation)).append("\n");
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
