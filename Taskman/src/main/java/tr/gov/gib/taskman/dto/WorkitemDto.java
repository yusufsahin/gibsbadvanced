package tr.gov.gib.taskman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkitemDto {
    private  Long id;

    private String name;

    private String description;

    private Set<TaskDto> tasks;
}
