package academy.mindswap.schoolpark.schoolpark.repository;

import academy.mindswap.schoolpark.schoolpark.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
