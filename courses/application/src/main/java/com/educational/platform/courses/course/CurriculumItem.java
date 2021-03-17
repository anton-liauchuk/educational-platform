package com.educational.platform.courses.course;

import java.util.UUID;

import javax.persistence.DiscriminatorColumn;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class CurriculumItem {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	private UUID uuid;

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
		this.uuid = UUID.randomUUID();
		this.title = title;
		this.description = description;
		this.course = course;
		this.serialNumber = serialNumber;
	}
}
