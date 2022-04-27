package tickethandler.partner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickethandler.partner.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
