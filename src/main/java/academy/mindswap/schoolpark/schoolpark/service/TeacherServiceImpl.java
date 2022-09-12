package academy.mindswap.schoolpark.schoolpark.service;

import academy.mindswap.schoolpark.schoolpark.command.*;
import academy.mindswap.schoolpark.schoolpark.model.Teacher;
import academy.mindswap.schoolpark.schoolpark.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final VehicleService vehicleService;

    public TeacherServiceImpl(TeacherRepository teacherRepository, VehicleService vehicleService) {
        this.teacherRepository = teacherRepository;
        this.vehicleService = vehicleService;
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<TeacherDTO> listAllTeachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teacher -> listAllTeachers.add(TeacherConverter.convertToDTO(teacher)));
        return listAllTeachers;
    }

    @Override
    public TeacherDTO createTeacher(CreateTeacherDTO createTeacherDTO) {
        return TeacherConverter.convertToDTO(teacherRepository.save(TeacherConverter.convertCreateTeacherDTOtoEntity(createTeacherDTO)));
    }

    @Override
    public String createVehicle(Integer teacherID, CreateVehicleDTO createVehicleDTO) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherID);
        if(teacher.isPresent()){
            return vehicleService.createVehicle(teacher.get(),
                    VehicleConverter.vehicleToCreateVehicleDTO(createVehicleDTO));
        }
        return "That Teacher ID doesn't exist!";
    }

    @Override
    public TeacherDTO findTeacherByID(Integer teacherID) {
        return teacherRepository.findById(teacherID).isEmpty() ? null :
                TeacherConverter.convertToDTO(teacherRepository.findById(teacherID).get());
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllCars();
    }

    @Override
    public TeacherDTO getOwnerByID(Integer vehicleID) {
        return TeacherConverter.convertToDTO(vehicleService.getOwnerByVehicleID(vehicleID));
    }

    @Override
    public String deleteTeacherByID(Integer teacherID) {
        if (teacherRepository.findById(teacherID).isEmpty()){
            return "Teacher ID not found!";
        }
        vehicleService.deleteAllCarsByTeacherID(teacherID);
        teacherRepository.deleteById(teacherID);
        return "Deleted with success!";
    }

    @Override
    public String deleteCarByID(Integer vehicleID) {
        return vehicleService.deleteCarByID(vehicleID);
    }

    @Override
    public List<VehicleDTO> getAllVehiclesByTeacherID(Integer teacherID) {
        return vehicleService.getAllCarsByTeacherID(teacherID);
    }
}
