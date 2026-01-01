package de.workshops.ticketeer.ticketvendor.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {

  /**
   * -- GETTER -- Get id
   *
   * @return id
   */
  private UUID id;

  /**
   * -- GETTER --
   *  Get name
   *
   * @return name
   */
  private String name;

  /**
   * -- GETTER --
   *  Get artists
   *
   * @return artists
   */
  private List<Artist> artists = new ArrayList<>();

  /**
   * -- GETTER --
   *  Get category
   *
   * @return category
   */
  private EventCategory category;

  /**
   * -- GETTER --
   *  Get date
   *
   * @return date
   */
  private OffsetDateTime date;

  /**
   * -- GETTER --
   *  Get location
   *
   * @return location
   */
  private Location location;

  /**
   * -- GETTER --
   *  Get tickets
   *
   * @return tickets
   */
  private List<Ticket> tickets = new ArrayList<>();

  /**
   * -- GETTER --
   *  Get status
   *
   * @return status
   */
  private EventStatus status;

  public Event() {
  }

  public Event id(UUID id) {
    this.id = id;
    return this;
  }


  public Event name(String name) {
    this.name = name;
    return this;
  }


  public Event artists(List<Artist> artists) {
    this.artists = artists;
    return this;
  }

  public Event addArtistsItem(Artist artistsItem) {
    if (this.artists == null) {
      this.artists = new ArrayList<>();
    }
    this.artists.add(artistsItem);
    return this;
  }

  public Event category(EventCategory category) {
    this.category = category;
    return this;
  }

  public Event date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  public Event location(Location location) {
    this.location = location;
    return this;
  }

  public Event tickets(List<Ticket> tickets) {
    this.tickets = tickets;
    return this;
  }

  public Event addTicketsItem(Ticket ticketsItem) {
    if (this.tickets == null) {
      this.tickets = new ArrayList<>();
    }
    this.tickets.add(ticketsItem);
    return this;
  }


  public Event status(EventStatus status) {
    this.status = status;
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
    Event event = (Event) o;
    return Objects.equals(this.id, event.id) &&
        Objects.equals(this.name, event.name) &&
        Objects.equals(this.artists, event.artists) &&
        Objects.equals(this.category, event.category) &&
        Objects.equals(this.date, event.date) &&
        Objects.equals(this.location, event.location) &&
        Objects.equals(this.tickets, event.tickets) &&
        Objects.equals(this.status, event.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, artists, category, date, location, tickets, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    artists: ").append(toIndentedString(artists)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    tickets: ").append(toIndentedString(tickets)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
