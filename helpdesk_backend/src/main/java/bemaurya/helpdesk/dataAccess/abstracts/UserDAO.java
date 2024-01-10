package bemaurya.helpdesk.dataAccess.abstracts;

import bemaurya.helpdesk.entities.concreates.RoleEnum;
import bemaurya.helpdesk.entities.concreates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {
    User getUserById(Long id);
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    List<User> findAllByRole(RoleEnum role);
}
