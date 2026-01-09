package com.educational.platform.course.enrollments.student;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.course.enrollments.student.create.CreateStudentCommand;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents student domain model.
 */
@Entity
public class Student implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    // for JPA
    private Student() {
    }

    public Student(CreateStudentCommand createStudentCommand) {
        this.username = createStudentCommand.username();
    }

    public Integer getId() {
        return id;
    }

    public String toReference() {
        return username;
    }
}
