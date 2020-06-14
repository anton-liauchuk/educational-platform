package com.educational.platform.courses.teacher;

import com.educational.platform.courses.teacher.create.CreateTeacherCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents Teacher model.
 */
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    // for JPA
    private Teacher() {

    }

    public Teacher(CreateTeacherCommand command) {
        this.username = command.getUsername();
    }

    public String toIdentity() {
        return username;
    }
}
