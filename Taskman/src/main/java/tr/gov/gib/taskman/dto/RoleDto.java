package tr.gov.gib.taskman.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private String description;
}
