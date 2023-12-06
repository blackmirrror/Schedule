package ru.blackmirrror.schedule.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.blackmirrror.schedule.models.Curriculum;
import ru.blackmirrror.schedule.repositories.CurriculumRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurriculumService {

    private final CurriculumRepository curriculumRepository;

    public List<Curriculum> findAll() {
        return curriculumRepository.findAll(Sort.by(Sort.Direction.ASC, "semester").and(Sort.by(Sort.Direction.ASC, "direction")));

    }

    public void createCurriculum(Curriculum curriculum) {
        curriculumRepository.save(curriculum);
    }
}
