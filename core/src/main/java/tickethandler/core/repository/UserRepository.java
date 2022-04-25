package tickethandler.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickethandler.core.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
