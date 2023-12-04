package ru.blackmirrror.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blackmirrror.schedule.models.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
    Group findGroupById(Long id);
}
