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

    public String getTimeByPair() {
        switch (this.pair) {
            case 1: return "09:00-10:30";
            case 2: return "10:40-12:10";
            case 3: return "12:40-14:10";
            case 4: return "14:20-15:50";
            case 5: return "16:20-17:50";
            case 6: return "18:00-19:30";
            default: return "19:40-21:00";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getPair() {
        return pair;
    }

    public void setPair(int pair) {
        this.pair = pair;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
}
