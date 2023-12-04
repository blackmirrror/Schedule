package ru.blackmirrror.schedule.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.blackmirrror.schedule.models.Direction;
import ru.blackmirrror.schedule.repositories.DirectionRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DirectionService {
    private final DirectionRepository directionRepository;

    public List<Direction> findAll() {
        return directionRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
    }

    public Direction findById(Long id) {
        return directionRepository.findDirectionById(id);
    }

    public void createDirection(Direction direction) {
        directionRepository.save(direction);
    }
}
