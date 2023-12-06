package ru.blackmirrror.schedule.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.blackmirrror.schedule.models.Classroom;
import ru.blackmirrror.schedule.models.Group;
import ru.blackmirrror.schedule.models.Schedule;
import ru.blackmirrror.schedule.models.User;
import ru.blackmirrror.schedule.repositories.ScheduleRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public void createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public Schedule findScheduleByClassroom(int pair, int dayOfWeek, Classroom classroom) {
        return scheduleRepository.findScheduleByPairAndDayOfWeekAndClassroom(pair, dayOfWeek, classroom);
    }
    public Schedule findScheduleByGroup(int pair, int dayOfWeek, Group group) {
        return scheduleRepository.findScheduleByPairAndDayOfWeekAndGroup(pair, dayOfWeek, group);
    }
    public Schedule findScheduleByTeacher(int pair, int dayOfWeek, User teacher) {
        return scheduleRepository.findScheduleByPairAndDayOfWeekAndTeacher(pair, dayOfWeek, teacher);
    }

    public List<Schedule> findAllByGroupAndSemester(Group group, int semester) {
        return scheduleRepository.findAllByGroupAndSemester(group, semester);
    }

    public List<Schedule> findAllByTeacherAndSemester(User teacher, int semester) {
        return scheduleRepository.findAllByTeacherAndSemester(teacher, semester);
    }
}
