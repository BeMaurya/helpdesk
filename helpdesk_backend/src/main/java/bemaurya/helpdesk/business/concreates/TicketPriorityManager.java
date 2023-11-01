package bemaurya.helpdesk.business.concreates;

import bemaurya.helpdesk.business.abstracts.TicketPriorityServices;
import bemaurya.helpdesk.core.utilities.result.Result;
import bemaurya.helpdesk.core.utilities.result.ResultData;
import bemaurya.helpdesk.dataAccess.abstracts.TicketPriorityDAO;
import bemaurya.helpdesk.entities.concreates.TicketPriority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketPriorityManager implements TicketPriorityServices {

    // this filed injection
    private TicketPriorityDAO ticketPriorityDAO;

    // constructor injection
    public TicketPriorityManager(TicketPriorityDAO ticketPriorityDAO) {
        this.ticketPriorityDAO = ticketPriorityDAO;
    }

    // this method is used for get all ticket
    @Override
    public ResultData<List<TicketPriority>> getAll() {
        return new ResultData<>(this.ticketPriorityDAO.findAll(), "Ticket priority list", true);
    }

    // this method is used for add ticket
    @Override
    public Result add(String priority) {
        try{
            this.ticketPriorityDAO.save(TicketPriority.builder().priority(priority).build());
        } catch (Exception e) {
            return new Result(false, "Ticket priority not added");
        }

        return new Result(true, "Ticket priority added");
    }

    // this method is used for delete ticket
    @Override
    public Result delete(long id) {
        return null;
    }
}
