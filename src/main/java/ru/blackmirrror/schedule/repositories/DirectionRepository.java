package ru.blackmirrror.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blackmirrror.schedule.models.Direction;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    Direction findDirectionById(Long id);
}
