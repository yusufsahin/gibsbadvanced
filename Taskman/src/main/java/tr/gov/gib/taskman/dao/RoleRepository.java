package tr.gov.gib.taskman.dao;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.gov.gib.taskman.dao.model.Role;

@Repository
@JaversSpringDataAuditable
public interface RoleRepository extends JpaRepository<Role,Long> {
 public Role findByName(String name);
}
