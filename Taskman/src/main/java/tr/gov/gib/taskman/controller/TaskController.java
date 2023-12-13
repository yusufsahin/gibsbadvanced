package tr.gov.gib.taskman.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.gov.gib.taskman.dto.TaskDto;
import tr.gov.gib.taskman.service.TaskService;
import tr.gov.gib.taskman.util.ApiPath;

import java.util.List;

@RestController
@RequestMapping(ApiPath.TaskCtrl.CTRL)
public class TaskController {

    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks()
    {
        List<TaskDto> data= taskService.getAll();
        return  ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<TaskDto> getTaskById(@PathVariable Long id)
    {
        TaskDto taskDto= taskService.getById(id);
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto)
    {
        TaskDto newTask= taskService.create(taskDto);

        return ResponseEntity.ok(newTask);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<TaskDto> updateTask(@PathVariable Long id,@Valid @RequestBody TaskDto taskDto)
    {
        TaskDto updatedTask= taskService.update(taskDto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteTask(@PathVariable Long id)
    {
        taskService.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
}
