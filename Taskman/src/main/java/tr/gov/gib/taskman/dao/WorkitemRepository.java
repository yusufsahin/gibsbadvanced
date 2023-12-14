package tr.gov.gib.taskman.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.gov.gib.taskman.dao.model.Workitem;

@Repository
public interface WorkitemRepository extends JpaRepository<Workitem,Long> {
}
