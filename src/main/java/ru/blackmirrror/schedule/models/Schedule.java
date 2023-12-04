package ru.blackmirrror.schedule.models;

import javax.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int semester;
    private int dayOfWeek;
    private int pair;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "classroomId")
    private Classroom classroom;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "groupId")
    private Group group;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "subjectId")
    private Subject subject;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "teacherId")
    private User teacher;
}
