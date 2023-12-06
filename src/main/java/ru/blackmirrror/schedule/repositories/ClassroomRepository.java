package ru.blackmirrror.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blackmirrror.schedule.models.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findClassroomById(Long id);
}
