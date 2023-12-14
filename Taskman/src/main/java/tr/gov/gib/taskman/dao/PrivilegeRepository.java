package tr.gov.gib.taskman.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.gov.gib.taskman.dao.model.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    public Privilege findByName(String name);
}
