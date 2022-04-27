package tickethandler.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickethandler.core.model.Userdevice;

@Repository
public interface UserdeviceRepository extends JpaRepository<Userdevice, Long> {
}
