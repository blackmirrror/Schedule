package ru.blackmirrror.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blackmirrror.schedule.models.Classroom;
import ru.blackmirrror.schedule.models.Group;
import ru.blackmirrror.schedule.models.Schedule;
import ru.blackmirrror.schedule.models.User;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findScheduleByPairAndDayOfWeekAndClassroom(int pair, int dayOfWeek, Classroom classroom);
    Schedule findScheduleByPairAndDayOfWeekAndGroup(int pair, int dayOfWeek, Group group);
    Schedule findScheduleByPairAndDayOfWeekAndTeacher(int pair, int dayOfWeek, User teacher);
    List<Schedule> findAllByGroupAndSemester(Group group, int semester);
    List<Schedule> findAllByTeacherAndSemester(User teacher, int semester);
}
