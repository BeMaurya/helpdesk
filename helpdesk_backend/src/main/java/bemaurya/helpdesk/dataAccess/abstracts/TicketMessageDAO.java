package bemaurya.helpdesk.dataAccess.abstracts;

import bemaurya.helpdesk.entities.concreates.TicketMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketMessageDAO extends JpaRepository<TicketMessage, Long> {
    List<TicketMessage> findAllByTicketId(long id);
}
