package ru.blackmirrror.schedule.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.blackmirrror.schedule.models.Subject;
import ru.blackmirrror.schedule.repositories.SubjectRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<Subject> findAll() {
        return subjectRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public void createSubject(Subject subject) {
        subjectRepository.save(subject);
    }
}
