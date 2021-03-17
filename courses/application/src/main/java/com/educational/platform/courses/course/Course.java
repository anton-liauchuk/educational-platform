package com.educational.platform.courses.course;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.courses.course.create.CreateCourseCommand;

/**
 * Represents Course domain model.
 */
@Entity
public class Course implements AggregateRoot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private UUID uuid;

	private String name;
	private String description;

	@Enumerated(EnumType.STRING)
	private PublishStatus publishStatus;

	@Enumerated(EnumType.STRING)
	private ApprovalStatus approvalStatus;

	private CourseRating rating;
	private NumberOfStudents numberOfStudents;

	private Integer teacher;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CurriculumItem> curriculumItems;

	// for JPA
	private Course() {

	}

	// todo remove public access + architecture tests
	public Course(CreateCourseCommand command, Integer teacher) {
		this.uuid = UUID.randomUUID();
		this.name = command.getName();
		this.description = command.getDescription();
		this.rating = new CourseRating(0);
		this.numberOfStudents = new NumberOfStudents(0);
		this.publishStatus = PublishStatus.DRAFT;
		this.approvalStatus = ApprovalStatus.NOT_SENT_FOR_APPROVAL;
		this.teacher = teacher;
		if (command.getCurriculumItems() != null) {
			this.curriculumItems = command
					.getCurriculumItems()
					.stream()
					.map(item -> CurriculumItemFactory.createFrom(item, this))
					.collect(Collectors.toList());
		}
	}

	public void approve() {
		approvalStatus = ApprovalStatus.APPROVED;
	}

	public void decline() {
		approvalStatus = ApprovalStatus.DECLINED;
	}

	public void sendToApprove() {
		if (approvalStatus == ApprovalStatus.APPROVED) {
			throw new CourseAlreadyApprovedException(uuid);
		}

		approvalStatus = ApprovalStatus.WAITING_FOR_APPROVAL;
	}

	public void publish() {
		if (approvalStatus != ApprovalStatus.APPROVED) {
			throw new CourseCannotBePublishedException(uuid);
		}

		publishStatus = PublishStatus.PUBLISHED;
	}

	public void archive() {
		publishStatus = PublishStatus.ARCHIVED;
	}

	public void updateRating(double value) {
		rating = new CourseRating(value);
	}

	//todo how it works in concurrency
	public void increaseNumberOfStudents() {
		numberOfStudents = new NumberOfStudents(numberOfStudents.getNumber() + 1);
	}

	// todo implement
	public void addLecture() {

	}

	// todo implement
	public void removeLecture() {

	}

	public UUID toIdentity() {
		return uuid;
	}
}
