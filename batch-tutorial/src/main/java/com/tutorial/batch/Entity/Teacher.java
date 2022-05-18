package com.tutorial.batch.Entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Teacher {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Id
    private Long id;
    private String name;
    private String subject;

    /*
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    @Builder
    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public void addStudent(Student student){
        students.add(student);
        student.setTeacher(this);
    }
     */
}
