package academy.mindswap.schoolpark.schoolpark.service;

import academy.mindswap.schoolpark.schoolpark.aop.LoggerExecutionTime;
import academy.mindswap.schoolpark.schoolpark.command.*;
import academy.mindswap.schoolpark.schoolpark.exception.NotFoundException;
import academy.mindswap.schoolpark.schoolpark.model.Role;
import academy.mindswap.schoolpark.schoolpark.model.Teacher;
import academy.mindswap.schoolpark.schoolpark.repository.RoleRepository;
import academy.mindswap.schoolpark.schoolpark.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final VehicleService vehicleService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public TeacherServiceImpl(TeacherRepository teacherRepository, VehicleService vehicleService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.teacherRepository = teacherRepository;
        this.vehicleService = vehicleService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @LoggerExecutionTime
    public List<TeacherDTO> getAllTeachers() {
        List<TeacherDTO> listAllTeachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teacher -> listAllTeachers.add(TeacherConverter.convertToDTO(teacher)));
        return listAllTeachers;
    }

    public List<Teacher> getAllTeachersForLogin(){
        return teacherRepository.findAll();
    }

    @Override
    public TeacherDTO createTeacher(CreateTeacherDTO createTeacherDTO) {
        createTeacherDTO.setPassword(passwordEncoder.encode(createTeacherDTO.getPassword()));
        return TeacherConverter.convertToDTO(teacherRepository.save(TeacherConverter.convertCreateTeacherDTOtoEntity(createTeacherDTO)));
    }

    @Override
    public String createVehicle(Integer teacherID, CreateVehicleDTO createVehicleDTO) {
        Teacher teacher = teacherRepository.findById(teacherID)
                .orElseThrow(() -> new NotFoundException ("This Teacher ID does not exist"));
        return vehicleService.createVehicle(teacher,
                VehicleConverter.vehicleToCreateVehicleDTO(createVehicleDTO));
    }

    @Override
    public TeacherDTO findTeacherByID(Integer teacherID) {
        return TeacherConverter.convertToDTO(teacherRepository
                .findById(teacherID).orElseThrow(()-> new NotFoundException("This Teacher ID does not exist")));
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
        teacherRepository.findById(teacherID).orElseThrow(()->new NotFoundException("Teacher ID does not exists"));
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

    @Override
    public ResponseEntity<String> addTeacherRole(Integer teacherID, Integer roleID) {
        Role role = roleRepository.findById(roleID).orElseThrow(()->new NotFoundException("Role not found."));
        role.getTeachers().add(teacherRepository.findById(teacherID).orElseThrow(()->new NotFoundException("Teacher ID not found.")));
        roleRepository.save(role);
        return new ResponseEntity<>("Role saved with success", HttpStatus.ACCEPTED);
    }
}
