package tr.gov.gib.taskman.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.gov.gib.taskman.dao.NoteRepository;
import tr.gov.gib.taskman.dao.model.Note;
import tr.gov.gib.taskman.dto.NoteDto;
import tr.gov.gib.taskman.service.NoteService;
import tr.gov.gib.taskman.util.TPage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public NoteDto create(NoteDto noteDto) {

        Note note= modelMapper.map(noteDto, Note.class);
        Note newNote= noteRepository.save(note);

        return modelMapper.map(newNote, NoteDto.class);
    }

    @Override
    public NoteDto getById(Long id) {

        Note note= noteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Note not found with id: " + id));
        return modelMapper.map(note, NoteDto.class);
    }

    @Override
    public List<NoteDto> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream()
                .map(note -> modelMapper.map(note, NoteDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public TPage<NoteDto> getAllPageable(Pageable pageable) {
        Page<Note> notes= noteRepository.findAll(pageable);
        List<NoteDto> noteDtos= notes.getContent().stream().map(
                note -> modelMapper.map(note, NoteDto.class)
        ).collect(Collectors.toList());

        TPage<NoteDto> noteDtoTPage= new TPage<>();
        noteDtoTPage.setPageData(notes,noteDtos);
        return noteDtoTPage;
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public NoteDto update(Long id, NoteDto noteDto) {
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id: " + id));
        modelMapper.map(noteDto, existingNote);
        existingNote = noteRepository.save(existingNote);
        return modelMapper.map(existingNote, NoteDto.class);
    }
}
