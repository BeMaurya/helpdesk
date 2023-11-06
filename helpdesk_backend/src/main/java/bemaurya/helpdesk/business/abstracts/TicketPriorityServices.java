package bemaurya.helpdesk.business.abstracts;

import bemaurya.helpdesk.core.utilities.result.Result;
import bemaurya.helpdesk.core.utilities.result.ResultData;
import bemaurya.helpdesk.entities.concreates.TicketPriority;

import java.util.List;

public interface TicketPriorityServices {
    ResultData<List<TicketPriority>> getAll(String token);
    Result add(String token, String priority);
    Result delete(String token, long id);
}