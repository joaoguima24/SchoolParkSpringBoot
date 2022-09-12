package academy.mindswap.schoolpark.schoolpark.service;

import academy.mindswap.schoolpark.schoolpark.command.*;
import academy.mindswap.schoolpark.schoolpark.model.Teacher;
import academy.mindswap.schoolpark.schoolpark.model.Vehicle;
import academy.mindswap.schoolpark.schoolpark.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final VehicleService vehicleService;

    public TeacherService(TeacherRepository teacherRepository, VehicleService vehicleService) {
        this.teacherRepository = teacherRepository;
        this.vehicleService = vehicleService;
    }

    public List<TeacherDTO> getAllTeachers() {
        List<TeacherDTO> listAllTeachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teacher -> listAllTeachers.add(TeacherConverter.convertToDTO(teacher)));
        return listAllTeachers;
    }

    public TeacherDTO createTeacher(CreateTeacherDTO createTeacherDTO) {
        return TeacherConverter.convertToDTO(teacherRepository.save(TeacherConverter.convertCreateTeacherDTOtoEntity(createTeacherDTO)));
    }

    public String createVehicle(Integer teacherID, CreateVehicleDTO createVehicleDTO) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherID);
        if(teacher.isPresent()){
            return vehicleService.createVehicle(teacher.get(),
                    VehicleConverter.vehicleToCreateVehicleDTO(createVehicleDTO));
        }
        return "That Teacher ID doesn't exist!";
    }

    public TeacherDTO findTeacherByID(Integer teacherID) {
        return teacherRepository.findById(teacherID).isEmpty() ? null :
                TeacherConverter.convertToDTO(teacherRepository.findById(teacherID).get());
    }

    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllCars();
    }

    public TeacherDTO getOwnerByID(Integer vehicleID) {
        return TeacherConverter.convertToDTO(vehicleService.getOwnerByVehicleID(vehicleID));
    }

    public String deleteTeacherByID(Integer teacherID) {
        if (teacherRepository.findById(teacherID).isEmpty()){
            return "Teacher ID not found!";
        }
        vehicleService.deleteAllCarsByTeacherID(teacherID);
        teacherRepository.deleteById(teacherID);
        return "Deleted with success!";
    }

    public String deleteCarByID(Integer vehicleID) {
        return vehicleService.deleteCarByID(vehicleID);
    }

    public List<VehicleDTO> getAllVehiclesByTeacherID(Integer teacherID) {
        return vehicleService.getAllCarsByTeacherID(teacherID);
    }
}
