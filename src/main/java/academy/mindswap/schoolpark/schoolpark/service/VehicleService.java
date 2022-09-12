package academy.mindswap.schoolpark.schoolpark.service;

import academy.mindswap.schoolpark.schoolpark.command.VehicleConverter;
import academy.mindswap.schoolpark.schoolpark.command.VehicleDTO;
import academy.mindswap.schoolpark.schoolpark.model.Teacher;
import academy.mindswap.schoolpark.schoolpark.model.Vehicle;
import academy.mindswap.schoolpark.schoolpark.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public String createVehicle(Teacher teacher , Vehicle vehicle) {
        vehicle.setOwner(teacher);
        vehicleRepository.save(vehicle);
        return "Car saved with success!";
    }

    public List<VehicleDTO> getAllCars() {
        List<VehicleDTO> listAllVehicles = new ArrayList<>();
        vehicleRepository.findAll().forEach(vehicle -> listAllVehicles.add(VehicleConverter.VehicleDTO(vehicle)));
        return listAllVehicles;
    }

    public Teacher getOwnerByVehicleID(Integer vehicleID) {
        if ((vehicleRepository.findById(vehicleID).isEmpty())
                || vehicleRepository.findById(vehicleID).get().getOwner() == null){
            return null;
        }
        return vehicleRepository.findById(vehicleID).get().getOwner();
    }

    public String deleteCarByID(Integer vehicleID) {
        vehicleRepository.deleteById(vehicleID);
        return "Vehicle deleted with success";
    }

    public void deleteAllCarsByTeacherID(Integer teacherID) {
        vehicleRepository.findAll().forEach(vehicle-> {
            if (vehicle.getOwner() != null && vehicle.getOwner().getID() == teacherID ){
                vehicleRepository.deleteById(vehicle.getID());
            }
        });
    }

    public List<VehicleDTO> getAllCarsByTeacherID(Integer teacherID) {
        List<VehicleDTO> vehicles = new ArrayList<>();
        vehicleRepository.findAll().forEach(vehicle -> {
            if (vehicle.getOwner()!=null && vehicle.getOwner().getID() == teacherID){
                vehicles.add(VehicleConverter.VehicleDTO(vehicle));
            }
        });
        return vehicles;
    }
}
