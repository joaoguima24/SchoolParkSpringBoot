package academy.mindswap.schoolpark.schoolpark.command;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VehicleDTO {
    private Integer ID;
    private String typeOfVehicle;


}
