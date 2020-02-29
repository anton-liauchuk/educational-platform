package com.educational.platform.courses.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Represents Course domain model.
 */
@Getter
@Setter
@Entity
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private String description;
  private Status status;
  private Teacher teacher;
  private CourseRating rating;

}
