package tr.gov.gib.taskman.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.gov.gib.taskman.dao.model.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByForgotPasswordGuid(UUID forgotPasswordGuid);
}
