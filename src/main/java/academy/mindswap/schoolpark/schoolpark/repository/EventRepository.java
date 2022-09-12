package academy.mindswap.schoolpark.schoolpark.repository;

import academy.mindswap.schoolpark.schoolpark.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findEventsByTeachersID(Integer teacherID);
}
