package ru.blackmirrror.schedule.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.blackmirrror.schedule.models.Group;
import ru.blackmirrror.schedule.models.Role;
import ru.blackmirrror.schedule.models.Subject;
import ru.blackmirrror.schedule.models.User;
import ru.blackmirrror.schedule.repositories.SubjectRepository;
import ru.blackmirrror.schedule.repositories.UserRepository;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setChecked(true);
        userRepository.save(user);
        return true;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        User user = userRepository.findByEmail(principal.getName());
        userRepository.save(user);
        return user;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public void setGroupStudent(User user, Group group) {
        user.getGroups().add(group);
        // user.setChecked(false);
        userRepository.save(user);
    }

    public void setSubjectsTeacher(Long userId, List<Long> selectedSubjectIds) {
        User user = userRepository.findUserById(userId);
        List<Subject> selectedSubjects = new ArrayList<>();
        for (int i = 0; i < selectedSubjectIds.size(); i++) {
            selectedSubjects.add(subjectRepository.getById(selectedSubjectIds.get(i)));
        }
        user.setSubjects(new HashSet<>(selectedSubjects));
        userRepository.save(user);
    }

    public void banUser(Long id) {
        User user =userRepository.findUserById(id);
        user.setChecked(!user.isChecked());
        userRepository.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }
}
