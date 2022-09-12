package academy.mindswap.schoolpark.schoolpark.service;

import academy.mindswap.schoolpark.schoolpark.command.VehicleDTO;
import academy.mindswap.schoolpark.schoolpark.model.Teacher;
import academy.mindswap.schoolpark.schoolpark.model.Vehicle;

import java.util.List;

public interface VehicleService {
    String createVehicle(Teacher teacher, Vehicle vehicle);

    List<VehicleDTO> getAllCars();

    Teacher getOwnerByVehicleID(Integer vehicleID);

    String deleteCarByID(Integer vehicleID);

    void deleteAllCarsByTeacherID(Integer teacherID);

    List<VehicleDTO> getAllCarsByTeacherID(Integer teacherID);
}
