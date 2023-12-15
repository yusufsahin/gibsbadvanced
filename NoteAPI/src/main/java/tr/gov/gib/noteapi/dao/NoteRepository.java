package tr.gov.gib.noteapi.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import tr.gov.gib.noteapi.dao.document.Note;

@Repository
public interface NoteRepository extends ReactiveMongoRepository<Note,String> {
}
