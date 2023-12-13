package tr.gov.gib.taskman.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tr.gov.gib.taskman.dao.TaskRepository;
import tr.gov.gib.taskman.dao.model.Task;
import tr.gov.gib.taskman.dto.TaskDto;
import tr.gov.gib.taskman.service.TaskService;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {


    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskDto create(TaskDto taskDto) {

        Task task= mapToEntity(taskDto);
        Task newTask= taskRepository.save(task);
        return mapToDTO(newTask);
    }

    @Override
    public TaskDto getById(Long id) {
        Task task= taskRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("Task not found"+ id)
        );
        return mapToDTO(task);
    }

    @Override
    public List<TaskDto> getAll() {

        List<Task> data= taskRepository.findAll();
        return Arrays.asList(modelMapper.map(data, TaskDto[].class));
    }

    @Override
    public TaskDto update(TaskDto taskDto) {

        Task task= taskRepository.findById(taskDto.getId())
                .orElseThrow(()->new EntityNotFoundException("Task not found "+taskDto.getId()));
        modelMapper.map(taskDto,task);
        Task updatedTask= taskRepository.save(task);
        return mapToDTO(updatedTask);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    private  Task mapToEntity(TaskDto taskDto)
    {
        return  modelMapper.map(taskDto,Task.class);
    }

    private  TaskDto mapToDTO(Task task)
    {
        return  modelMapper.map(task,TaskDto.class);
    }
}
