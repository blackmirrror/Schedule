package ru.blackmirrror.schedule.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.blackmirrror.schedule.models.Group;
import ru.blackmirrror.schedule.repositories.GroupRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public Group findByName(String name) {
        return groupRepository.findByName(name);
    }

    public Group findById(Long id) {
        return groupRepository.findGroupById(id);
    }

    public List<Group> findAll() {
        return groupRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public void createGroup(Group group) {
        groupRepository.save(group);
    }
}
