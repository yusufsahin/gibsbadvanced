package tr.gov.gib.noteapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import tr.gov.gib.noteapi.dao.NoteRepository;
import tr.gov.gib.noteapi.dao.document.Note;
import tr.gov.gib.noteapi.service.impl.NoteServiceImpl;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;
    @InjectMocks
    private NoteServiceImpl noteService;
    @Test
    void testCreateNote() {
        Note note = new Note("Test", "Content");
        when(noteRepository.save(note)).thenReturn(Mono.just(note));

        StepVerifier.create(noteService.createNote(note))
                .expectNextMatches(createdNote -> createdNote.getTitle().equals("Test"))
                .verifyComplete();
    }

    @Test
    void testFindNoteById() {
        Note note = new Note("Test", "Content");
        when(noteRepository.findById("1")).thenReturn(Mono.just(note));

        StepVerifier.create(noteService.findNoteById("1"))
                .expectNextMatches(foundNote -> foundNote.getTitle().equals("Test"))
                .verifyComplete();
    }

    @Test
    void testFindAllNotes() {
        Note note1 = new Note("Test1", "Content1");
        Note note2 = new Note("Test2", "Content2");
        when(noteRepository.findAll()).thenReturn(Flux.just(note1, note2));

        StepVerifier.create(noteService.findAllNotes())
                .expectNextCount(2)  // Explicitly checking the number of elements emitted
                .verifyComplete();
    }

    @Test
    void testDeleteNote() {
        when(noteRepository.deleteById("1")).thenReturn(Mono.empty());

        StepVerifier.create(noteService.deleteNote("1"))
                .verifyComplete();
    }

}
