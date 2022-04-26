package tickethandler.partner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seats")
@NoArgsConstructor
@Getter
@Setter
public class Seat {

    @Id
    @Column(name = "seat_id", nullable = false)
    private Long seatId;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "reserved", nullable = false)
    private Boolean reserved;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

}
