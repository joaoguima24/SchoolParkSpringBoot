package academy.mindswap.schoolpark.schoolpark.repository;

import academy.mindswap.schoolpark.schoolpark.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
