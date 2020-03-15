package com.educational.platform.courses.course;

import javax.persistence.*;

/**
 * Represents Lecture domain model.
 */
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Course course;

    private String name;
    private String description;
    private String content;

}
