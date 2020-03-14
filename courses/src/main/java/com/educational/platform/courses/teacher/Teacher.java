package com.educational.platform.courses.teacher;

import com.educational.platform.courses.teacher.create.CreateTeacherCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents Teacher model.
 */
// todo relation to user from user module
@Getter
@Setter
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;


    public Teacher(CreateTeacherCommand command) {
        this.username = command.getUsername();
    }
}
