package tickethandler.partner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tickethandler.partner.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
