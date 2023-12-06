package ru.blackmirrror.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blackmirrror.schedule.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findSubjectById(Long id);
}
