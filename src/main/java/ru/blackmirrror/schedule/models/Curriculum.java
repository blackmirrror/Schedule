package ru.blackmirrror.schedule.models;

import javax.persistence.*;

@Entity
@Table(name = "curriculums")
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int semester;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "directionId")
    private Direction direction;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "subjectId")
    private Subject subject;
    private int countPerSemester;

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getCountPerSemester() {
        return countPerSemester;
    }

    public void setCountPerSemester(int countPerSemester) {
        this.countPerSemester = countPerSemester;
    }
}
