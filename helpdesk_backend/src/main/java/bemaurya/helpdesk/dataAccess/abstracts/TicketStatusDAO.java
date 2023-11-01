package bemaurya.helpdesk.dataAccess.abstracts;

import bemaurya.helpdesk.entities.concreates.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketStatusDAO extends JpaRepository<TicketStatus, Long> {
}
