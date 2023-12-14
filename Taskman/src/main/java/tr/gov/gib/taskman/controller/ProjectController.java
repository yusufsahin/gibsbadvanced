package tr.gov.gib.taskman.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.gov.gib.taskman.dto.ProjectDto;
import tr.gov.gib.taskman.dto.WorkitemDto;
import tr.gov.gib.taskman.service.ProjectService;
import tr.gov.gib.taskman.service.WorkitemService;
import tr.gov.gib.taskman.util.ApiPath;

import java.util.List;

@RestController
@RequestMapping(ApiPath.ProjectCtrl.CTRL)
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllWorkitems()
    {
        List<ProjectDto> data= projectService.getProjects();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id)
    {
        ProjectDto projectDto= projectService.getByProjectId(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto)
    {
        ProjectDto newProject= projectService.create(projectDto);
        return ResponseEntity.ok(newProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id,@Valid @RequestBody ProjectDto projectDto)
    {
        ProjectDto updatedProject= projectService.update(projectDto);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id)
    {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
