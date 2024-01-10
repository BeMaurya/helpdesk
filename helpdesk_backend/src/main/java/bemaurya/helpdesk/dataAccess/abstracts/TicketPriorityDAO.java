package bemaurya.helpdesk.dataAccess.abstracts;

import bemaurya.helpdesk.entities.concreates.TicketPriority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPriorityDAO extends JpaRepository<TicketPriority,Long> {
}
