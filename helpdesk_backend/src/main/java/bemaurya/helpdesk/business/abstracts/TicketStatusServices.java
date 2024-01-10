package bemaurya.helpdesk.business.abstracts;

import bemaurya.helpdesk.core.utilities.result.Result;
import bemaurya.helpdesk.core.utilities.result.ResultData;
import bemaurya.helpdesk.entities.concreates.TicketStatus;

import java.util.List;

public interface TicketStatusServices {
    ResultData<List<TicketStatus>> getAll(String token);
    Result add(String token,String status);
    Result delete(String token,long id);

}
