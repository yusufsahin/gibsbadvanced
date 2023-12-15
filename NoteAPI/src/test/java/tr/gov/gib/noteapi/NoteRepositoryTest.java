package tr.gov.gib.noteapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;
import tr.gov.gib.noteapi.dao.NoteRepository;
import tr.gov.gib.noteapi.dao.document.Note;

@DataMongoTest
public class NoteRepositoryTest {
    @Autowired
    private NoteRepository noteRepository;

    @Test
    void testSave() {
        Note note = new Note("Test", "Content");
        StepVerifier.create(noteRepository.save(note))
                .expectNextMatches(savedNote -> savedNote.getId() != null && savedNote.getTitle().equals("Test"))
                .verifyComplete();
    }

    @Test
    void testFindById() {
        Note note = new Note("Test", "Content");
        noteRepository.save(note).block();
        StepVerifier.create(noteRepository.findById(note.getId()))
                .expectNextMatches(foundNote -> foundNote.getTitle().equals("Test"))
                .verifyComplete();
    }

    @Test
    void testDelete() {
        Note note = new Note("Test", "Content");
        String id = noteRepository.save(note).block().getId();
        StepVerifier.create(noteRepository.deleteById(id))
                .verifyComplete();
        StepVerifier.create(noteRepository.findById(id))
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void testFindAll() {
        Note note1 = new Note("Test1", "Content1");
        Note note2 = new Note("Test2", "Content2");
        noteRepository.save(note1).block();
        noteRepository.save(note2).block();

        StepVerifier.create(noteRepository.findAll())
                .expectNextCount(2)
                .verifyComplete();
    }
}
