package bemaurya.helpdesk.business.abstracts;

import bemaurya.helpdesk.core.utilities.result.Result;
import bemaurya.helpdesk.core.utilities.result.ResultData;
import bemaurya.helpdesk.dto.request.TicketMessage.TicketMessageRequest;
import bemaurya.helpdesk.entities.concreates.TicketMessage;

import java.util.List;

public interface TicketMessageServices {
    ResultData<List<TicketMessage>> getAll();
    Result add(TicketMessageRequest TicketMessage, String token);
    Result delete(long id, String token);
    ResultData<TicketMessage> getById(long id);
}
