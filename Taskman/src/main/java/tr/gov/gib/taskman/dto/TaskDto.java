package tr.gov.gib.taskman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.gov.gib.taskman.enumerate.TaskStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
}
