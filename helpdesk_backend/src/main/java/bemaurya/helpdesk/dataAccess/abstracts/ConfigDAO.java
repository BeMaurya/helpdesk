package bemaurya.helpdesk.dataAccess.abstracts;

import bemaurya.helpdesk.entities.concreates.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigDAO extends JpaRepository<Config, Long> {
}
