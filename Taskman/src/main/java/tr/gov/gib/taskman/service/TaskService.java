package tr.gov.gib.taskman.service;

import tr.gov.gib.taskman.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto create(TaskDto taskDto);
    TaskDto getById(Long id);
    List<TaskDto> getAll();

    TaskDto update(TaskDto taskDto);

    void deleteById(Long id);
}
