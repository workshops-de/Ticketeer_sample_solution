package de.workshops.ticketeer.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  Long id;

  @Column(nullable = false)
  String name;

  String venue;

  LocalDateTime startDateTime;

  BigDecimal basePrice;

  Integer capacity;

  Integer remainingTickets;

  Boolean externalVendorManaged = false;

  UUID externalVendorId;

  /// Additionally, you can add a `@Conver(converter = EventStatusConverter.class)` annotation to convert between database and Java types.
  /// This is only necessary if the `@Converter` annotation in Class `EventStatusConverter` is NOT set to `autoApply = true`.
  @Column(nullable = false, columnDefinition = "varchar(255) default 'DRAFT'")
  @Builder.Default
  EventStatus status = EventStatus.DRAFT;

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    Class<?> oEffectiveClass = o instanceof HibernateProxy
        ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
        : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    Event event = (Event) o;
    return getId() != null && Objects.equals(getId(), event.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }
}
