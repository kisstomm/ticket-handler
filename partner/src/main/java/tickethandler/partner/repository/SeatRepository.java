package tickethandler.partner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickethandler.partner.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
