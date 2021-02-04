package com.educational.platform.courses.course;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Represents Curriculum Item domain model.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CurriculumItem {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	private String title;
	private String description;
	private Integer serialNumber;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	// for JPA
	protected CurriculumItem() {

	}

	protected CurriculumItem(String title, String description, Course course, Integer serialNumber) {
		this.title = title;
		this.description = description;
		this.course = course;
		this.serialNumber = serialNumber;
	}
}
