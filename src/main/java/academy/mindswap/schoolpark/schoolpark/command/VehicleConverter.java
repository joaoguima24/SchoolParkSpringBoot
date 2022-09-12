package academy.mindswap.schoolpark.schoolpark.command;

import academy.mindswap.schoolpark.schoolpark.model.Vehicle;

public class VehicleConverter {
    public static Vehicle vehicleDTOToEntity (VehicleDTO vehicleDTO){
        return Vehicle.builder()
                .ID(vehicleDTO.getID())
                .typeOfVehicle(vehicleDTO.getTypeOfVehicle())
                .build();
    }

    public static Vehicle vehicleToCreateVehicleDTO (CreateVehicleDTO createVehicleDTO){
        return Vehicle.builder()
                .typeOfVehicle(createVehicleDTO.getTypeOfVehicle())
                .owner(createVehicleDTO.getOwner())
                .build();
    }

    public static VehicleDTO VehicleDTO (Vehicle vehicle){
        return VehicleDTO.builder()
                .ID(vehicle.getID())
                .typeOfVehicle(vehicle.getTypeOfVehicle())
                .build();
    }

}
