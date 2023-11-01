package bemaurya.helpdesk.dataAccess.abstracts;

import bemaurya.helpdesk.entities.concreates.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDAO extends JpaRepository<Ticket, Long> {
    Page<Ticket> findAllByUserId(long id, PageRequest pageRequest);
}
