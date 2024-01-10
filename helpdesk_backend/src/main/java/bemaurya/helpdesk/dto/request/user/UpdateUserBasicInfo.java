package bemaurya.helpdesk.dto.request.user;


import bemaurya.helpdesk.entities.concreates.RoleEnum;
import bemaurya.helpdesk.entities.concreates.UserTitle;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class UpdateUserBasicInfo {
    private String name;
    private String surname;
    private String email;
}