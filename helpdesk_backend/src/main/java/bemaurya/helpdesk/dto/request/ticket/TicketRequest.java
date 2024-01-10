package bemaurya.helpdesk.dto.request.ticket;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class TicketRequest {
    private String title;
    private String description;
    private long status;
    private long priority;
}
