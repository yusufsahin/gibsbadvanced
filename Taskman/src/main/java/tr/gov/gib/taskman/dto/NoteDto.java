package tr.gov.gib.taskman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
    private Long id;
    private String name;
    private String description;
    private String memo;
}
