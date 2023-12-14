package tr.gov.gib.taskman.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private  Long id;

    @NotNull
    private String username;

    @NotNull
    private  String email;

    private  String firstname;
    private  String lastname;

    private  String phonenum;

    private byte[] picture;
}
