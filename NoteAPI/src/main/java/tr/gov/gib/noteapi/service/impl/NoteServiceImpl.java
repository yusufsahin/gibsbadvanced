package tr.gov.gib.noteapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.gov.gib.noteapi.dao.NoteRepository;
import tr.gov.gib.noteapi.dao.document.Note;
import tr.gov.gib.noteapi.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Mono<Note> createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Flux<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Mono<Note> findNoteById(String id) {
        return noteRepository.findById(id);
    }

    @Override
    public Mono<Note> updateNote(String id, Note note) {
        return noteRepository.findById(id)
                .flatMap(existingNote -> {
                    existingNote.setTitle(note.getTitle());
                    existingNote.setContent(note.getContent());
                    return noteRepository.save(existingNote);
                });
    }

    @Override
    public Mono<Void> deleteNote(String id) {
        return noteRepository.deleteById(id);
    }
}
