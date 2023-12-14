package tr.gov.gib.taskman.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.gov.gib.taskman.dto.WorkitemDto;
import tr.gov.gib.taskman.service.WorkitemService;
import tr.gov.gib.taskman.util.ApiPath;

import java.util.List;

@RestController
@RequestMapping(ApiPath.WorkitemCtrl.CTRL)
public class WorkitemController {

    private WorkitemService workitemService;

    @Autowired
    public WorkitemController(WorkitemService workitemService) {
        this.workitemService = workitemService;
    }

    @GetMapping
    public ResponseEntity<List<WorkitemDto>> getAllWorkitems()
    {
        List<WorkitemDto> data= workitemService.getAllWorkitems();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkitemDto> getWorkitemById(@PathVariable Long id)
    {
        WorkitemDto workitemDto= workitemService.getById(id);
        return ResponseEntity.ok(workitemDto);
    }

    @PostMapping
    public ResponseEntity<WorkitemDto> createWorkitem(@Valid @RequestBody WorkitemDto workitemDto)
    {
        WorkitemDto newWorkitem= workitemService.create(workitemDto);
        return ResponseEntity.ok(newWorkitem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkitemDto> updateWorkitem(@PathVariable Long id,@Valid @RequestBody WorkitemDto workitemDto)
    {
        WorkitemDto updatedWorkitem= workitemService.update(workitemDto);
        return ResponseEntity.ok(updatedWorkitem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkitem(@PathVariable Long id)
    {
        workitemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
