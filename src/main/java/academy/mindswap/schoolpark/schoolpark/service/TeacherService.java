package academy.mindswap.schoolpark.schoolpark.service;

import academy.mindswap.schoolpark.schoolpark.command.CreateTeacherDTO;
import academy.mindswap.schoolpark.schoolpark.command.CreateVehicleDTO;
import academy.mindswap.schoolpark.schoolpark.command.TeacherDTO;
import academy.mindswap.schoolpark.schoolpark.command.VehicleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {
    List<TeacherDTO> getAllTeachers();

    TeacherDTO createTeacher(CreateTeacherDTO createTeacherDTO);

    String createVehicle(Integer teacherID, CreateVehicleDTO createVehicleDTO);

    TeacherDTO findTeacherByID(Integer teacherID);

    List<VehicleDTO> getAllVehicles();

    TeacherDTO getOwnerByID(Integer vehicleID);

    String deleteTeacherByID(Integer teacherID);

    String deleteCarByID(Integer vehicleID);

    List<VehicleDTO> getAllVehiclesByTeacherID(Integer teacherID);

    ResponseEntity<String> addTeacherRole(Integer teacherID, Integer roleID);
}
