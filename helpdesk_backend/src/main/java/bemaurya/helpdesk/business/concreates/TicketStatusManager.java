package bemaurya.helpdesk.business.concreates;

import bemaurya.helpdesk.business.abstracts.TicketStatusServices;
import bemaurya.helpdesk.core.utilities.result.Result;
import bemaurya.helpdesk.core.utilities.result.ResultData;
import bemaurya.helpdesk.dataAccess.abstracts.TicketStatusDAO;
import bemaurya.helpdesk.entities.concreates.TicketStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketStatusManager implements TicketStatusServices {
    // filed injection
    private final TicketStatusDAO ticketStatusDAO;

    // constructor injection
    public TicketStatusManager(TicketStatusDAO ticketStatusDAO) {
        this.ticketStatusDAO = ticketStatusDAO;
    }

    // this method is used to get all ticket status
    @Override
    public ResultData<List<TicketStatus>> getAll() {
        return new ResultData<>(this.ticketStatusDAO.findAll(), "ticket status list", true);
    }

    // this method is used to add ticket status
    @Override
    public Result add(String status) {
        try {
            this.ticketStatusDAO.save(TicketStatus.builder().status(status).build());
        } catch (Exception e) {
            return new Result(false, "Ticket Status Not Added");
        }
        return new Result(true, "Ticket Status Added");
    }

    // this method is used to delete ticket status
    @Override
    public Result delete(long id) {
        try {
            this.ticketStatusDAO.deleteById(id);
        } catch (Exception e) {
            return new Result(false, "Ticket Status Not Deleted");
        }
        return new Result(true, "Ticket Status Deleted");
    }

}
