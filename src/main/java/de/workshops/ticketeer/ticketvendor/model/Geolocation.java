package de.workshops.ticketeer.ticketvendor.model;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Geolocation {

  public static final String SERIALIZED_NAME_LATITUDE = "latitude";
  /**
   * -- GETTER -- Get latitude minimum: -90 maximum: 90
   *
   * @return latitude
   */
  private Double latitude;

  public static final String SERIALIZED_NAME_LONGITUDE = "longitude";
  /**
   * -- GETTER --
   *  Get longitude minimum: -180 maximum: 180
   *
   * @return longitude
   */
  private Double longitude;

  public Geolocation() {
  }

  public Geolocation latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }


  public Geolocation longitude(Double longitude) {
    this.longitude = longitude;
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
    Geolocation geolocation = (Geolocation) o;
    return Objects.equals(this.latitude, geolocation.latitude) &&
        Objects.equals(this.longitude, geolocation.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Geolocation {\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
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
