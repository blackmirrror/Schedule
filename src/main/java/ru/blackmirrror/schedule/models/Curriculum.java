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

}
