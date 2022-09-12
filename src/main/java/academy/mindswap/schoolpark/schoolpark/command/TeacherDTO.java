package academy.mindswap.schoolpark.schoolpark.command;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TeacherDTO {
    private Integer ID;
    private String firstName;
    private String lastName;
    private String email;
}
