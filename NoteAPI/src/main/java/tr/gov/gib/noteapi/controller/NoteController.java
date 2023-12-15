package tr.gov.gib.noteapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.gov.gib.noteapi.dao.document.Note;
import tr.gov.gib.noteapi.service.NoteService;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public Mono<Note> createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @GetMapping
    public Flux<Note> getAllNotes() {
        return noteService.findAllNotes();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Note>> getNoteById(@PathVariable String id) {
        return noteService.findNoteById(id)
                .map(note -> ResponseEntity.ok(note))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Note>> updateNote(@PathVariable String id, @RequestBody Note note) {
        return noteService.updateNote(id, note)
                .map(updatedNote -> ResponseEntity.ok(updatedNote))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteNote(@PathVariable String id) {
        return noteService.deleteNote(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
