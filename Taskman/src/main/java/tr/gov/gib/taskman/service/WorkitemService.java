package tr.gov.gib.taskman.service;

import tr.gov.gib.taskman.dto.WorkitemDto;

import java.util.List;

public interface WorkitemService {

    WorkitemDto create(WorkitemDto workitemDto);

    WorkitemDto getById(Long id);

    List<WorkitemDto> getAllWorkitems();

    WorkitemDto update(WorkitemDto workitemDto);

    void deleteById(Long id);
}
