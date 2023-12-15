package tr.gov.gib.noteapi.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.gov.gib.noteapi.dao.document.Note;

public interface NoteService {
    Mono<Note> createNote(Note note);
    Flux<Note> findAllNotes();
    Mono<Note> findNoteById(String id);
    Mono<Note> updateNote(String id, Note note);
    Mono<Void> deleteNote(String id);
}
