package tr.gov.gib.taskman.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.gov.gib.taskman.dto.ProjectDto;
import tr.gov.gib.taskman.dto.WorkitemDto;
import tr.gov.gib.taskman.service.ProjectService;
import tr.gov.gib.taskman.service.WorkitemService;
import tr.gov.gib.taskman.util.ApiPath;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiPath.ProjectCtrl.CTRL)
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {

        this.projectService = projectService;
    }
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllWorkitems()
    {
        List<ProjectDto> data= projectService.getProjects();
        return ResponseEntity.ok(data);
    }
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable(value = "projectid", required = true) Long projectid)
    {

        try {
            ProjectDto projectDto= projectService.getByProjectId(projectid);
            return ResponseEntity.ok(projectDto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //return 404, with null body
        }


    }
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto)
    {
        try
        {
        ProjectDto newProject= projectService.create(projectDto);
        return ResponseEntity.created(new URI(ApiPath.ProjectCtrl.CTRL+"/"+newProject.getId())).body(newProject);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id,@Valid @RequestBody ProjectDto projectDto)
    {
        try {
            projectService.update(projectDto);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id)
    { try {
        if(id!=null)
        {

            projectService.delete(id);
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.badRequest().build();
        }
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }
}
