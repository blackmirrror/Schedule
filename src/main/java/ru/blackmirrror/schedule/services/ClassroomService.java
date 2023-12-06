package ru.blackmirrror.schedule.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.blackmirrror.schedule.models.Classroom;
import ru.blackmirrror.schedule.repositories.ClassroomRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    public Classroom findById(Long id) {
        return classroomRepository.findClassroomById(id);
    }
}
