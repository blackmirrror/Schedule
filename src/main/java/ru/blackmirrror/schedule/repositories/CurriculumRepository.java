package ru.blackmirrror.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blackmirrror.schedule.models.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
