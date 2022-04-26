package tickethandler.partner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tickethandler.partner.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
