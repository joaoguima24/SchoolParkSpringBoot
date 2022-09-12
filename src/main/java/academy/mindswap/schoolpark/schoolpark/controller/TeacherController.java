package academy.mindswap.schoolpark.schoolpark.controller;

import academy.mindswap.schoolpark.schoolpark.command.*;
import academy.mindswap.schoolpark.schoolpark.service.TeacherService;
import academy.mindswap.schoolpark.schoolpark.service.TeacherServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers () {
        return new ResponseEntity<>(teacherService.getAllTeachers(),HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@Valid @RequestBody CreateTeacherDTO createTeacherDTO , BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error ->{
                errors.add(error.getDefaultMessage());
            } );
            return new ResponseEntity<>( errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(teacherService.createTeacher(createTeacherDTO), HttpStatus.CREATED);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles(){
        return new ResponseEntity<>(teacherService.getAllVehicles() , HttpStatus.ACCEPTED);
    }

    @GetMapping("/vehicles/{vehicleID}")
    public ResponseEntity<TeacherDTO> getOwnerByVehicleID(@PathVariable Integer vehicleID){
        return teacherService.getOwnerByID(vehicleID) == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST):
                new ResponseEntity<>(teacherService.getOwnerByID(vehicleID) ,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/vehicles/{vehicleID}")
    public ResponseEntity<String> deleteVehicleByID(@PathVariable Integer vehicleID){
        return new ResponseEntity<>(teacherService.deleteCarByID(vehicleID), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{teacherID}")
  //  @RequestMapping(value = "/{ID}" , method = RequestMethod.GET)
    public ResponseEntity<TeacherDTO> findTeacherByID(@PathVariable Integer teacherID){
        return teacherService.findTeacherByID(teacherID) != null ?
                new ResponseEntity<>(teacherService.findTeacherByID(teacherID), HttpStatus.FOUND) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{teacherID}")
    public ResponseEntity<String> deleteTeacherByID(@PathVariable Integer teacherID){
         return new ResponseEntity<>(teacherService.deleteTeacherByID(teacherID),HttpStatus.ACCEPTED);
    }

    @PostMapping("/{teacherID}/vehicle")
    public ResponseEntity<String> createVehicle(@PathVariable Integer teacherID, @RequestBody CreateVehicleDTO createVehicleDTO){
        return new ResponseEntity<>(teacherService.createVehicle(teacherID,createVehicleDTO),HttpStatus.CREATED);
    }

    @GetMapping("/{teacherID}/vehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehiclesByTeacherID(@PathVariable Integer teacherID){
        return new ResponseEntity<>(teacherService.getAllVehiclesByTeacherID(teacherID), HttpStatus.ACCEPTED);
    }

}
