package tickethandler.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickethandler.core.model.Usertoken;

@Repository
public interface UsertokenRepository extends JpaRepository<Usertoken, Long> {
}
