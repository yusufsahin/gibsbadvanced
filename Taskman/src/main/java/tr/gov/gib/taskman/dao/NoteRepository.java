package tr.gov.gib.taskman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tr.gov.gib.taskman.dao.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long>{
}
