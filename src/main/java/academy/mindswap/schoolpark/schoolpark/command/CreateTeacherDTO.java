package academy.mindswap.schoolpark.schoolpark.command;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.*;


@Builder
@Data
public class CreateTeacherDTO {
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank (message = "Last name is mandatory")
    private String lastName;
    @Email (message = "Email is mandatory")
    private String email;
    @Size(min = 8 , max = 20 , message = "Password as to be between 8 and 20 characters long")
    private String password;
}
