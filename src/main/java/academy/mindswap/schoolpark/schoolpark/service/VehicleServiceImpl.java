package academy.mindswap.schoolpark.schoolpark.service;

import academy.mindswap.schoolpark.schoolpark.command.VehicleConverter;
import academy.mindswap.schoolpark.schoolpark.command.VehicleDTO;
import academy.mindswap.schoolpark.schoolpark.model.Teacher;
import academy.mindswap.schoolpark.schoolpark.model.Vehicle;
import academy.mindswap.schoolpark.schoolpark.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public String createVehicle(Teacher teacher, Vehicle vehicle) {
        vehicle.setOwner(teacher);
        vehicleRepository.save(vehicle);
        return "Car saved with success!";
    }

    @Override
    public List<VehicleDTO> getAllCars() {
        List<VehicleDTO> listAllVehicles = new ArrayList<>();
        vehicleRepository.findAll().forEach(vehicle -> listAllVehicles.add(VehicleConverter.VehicleDTO(vehicle)));
        return listAllVehicles;
    }

    @Override
    public Teacher getOwnerByVehicleID(Integer vehicleID) {
        return vehicleRepository.findById(vehicleID).orElseThrow(()->new NotAcceptableStatusException("Vehicle not found"))
                .getOwner();
    }

    @Override
    public String deleteCarByID(Integer vehicleID) {
        vehicleRepository.deleteById(vehicleID);
        return "Vehicle deleted with success";
    }

    @Override
    public void deleteAllCarsByTeacherID(Integer teacherID) {
        vehicleRepository.findAll().forEach(vehicle-> {
            if (vehicle.getOwner() != null && vehicle.getOwner().getID() == teacherID ){
                vehicleRepository.deleteById(vehicle.getID());
            }
        });
    }

    @Override
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
