package tr.gov.gib.taskman.service;

import org.springframework.data.domain.Pageable;
import tr.gov.gib.taskman.dto.NoteDto;
import tr.gov.gib.taskman.util.TPage;

import java.util.List;

public interface NoteService {
    NoteDto create(NoteDto noteDto);
    NoteDto getById(Long id);
    List<NoteDto> getAllNotes();
    TPage<NoteDto> getAllPageable(Pageable pageable);
    void delete(Long id);

    NoteDto update(Long id, NoteDto noteDto);
}
