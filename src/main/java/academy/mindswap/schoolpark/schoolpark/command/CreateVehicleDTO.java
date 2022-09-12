package academy.mindswap.schoolpark.schoolpark.command;

import academy.mindswap.schoolpark.schoolpark.model.Teacher;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateVehicleDTO {
    private String typeOfVehicle;
    private Teacher owner;
}
