package tr.gov.gib.noteapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.gov.gib.noteapi.controller.NoteController;
import tr.gov.gib.noteapi.dao.document.Note;
import tr.gov.gib.noteapi.service.NoteService;

@WebFluxTest(NoteController.class)
public class NoteControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        // Setup mock behavior here if needed
    }
    @Test
    void testCreateNote() {
        Note note = new Note("Test", "Content");
        Mockito.when(noteService.createNote(Mockito.any(Note.class))).thenReturn(Mono.just(note));

        webTestClient.post().uri("/api/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(note)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Test");
    }
    @Test
    void testGetAllNotes() {
        Note note1 = new Note("Test1", "Content1");
        Note note2 = new Note("Test2", "Content2");
        Mockito.when(noteService.findAllNotes()).thenReturn(Flux.just(note1, note2));

        webTestClient.get().uri("/api/notes")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Note.class).hasSize(2);
    }

    @Test
    void testGetNoteById() {
        Note note = new Note("Test", "Content");
        Mockito.when(noteService.findNoteById("1")).thenReturn(Mono.just(note));

        webTestClient.get().uri("/api/notes/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Test");
    }

    @Test
    void testUpdateNote() {
        Note note = new Note("Updated", "Content");
        Mockito.when(noteService.updateNote(Mockito.anyString(), Mockito.any(Note.class))).thenReturn(Mono.just(note));

        webTestClient.put().uri("/api/notes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(note)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Updated");
    }

    @Test
    void testDeleteNote() {
        Mockito.when(noteService.deleteNote("1")).thenReturn(Mono.empty());

        webTestClient.delete().uri("/api/notes/1")
                .exchange()
                .expectStatus().isOk();
    }
}
