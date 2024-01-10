package bemaurya.helpdesk.dataAccess.abstracts;

import bemaurya.helpdesk.entities.concreates.UserTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTitleDAO extends JpaRepository<UserTitle, Long> {
    UserTitle getByTitle(String title);
}
