package tickethandler.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickethandler.core.model.Userbankcard;

@Repository
public interface UserbankcardRepository extends JpaRepository<Userbankcard, Long> {
}
