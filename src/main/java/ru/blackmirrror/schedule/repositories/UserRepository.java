package ru.blackmirrror.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blackmirrror.schedule.models.Role;
import ru.blackmirrror.schedule.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findUserById(Long id);
    List<User> findByRolesContains(Role role);


}
