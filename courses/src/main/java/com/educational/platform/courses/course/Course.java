package com.educational.platform.courses.course;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.teacher.Teacher;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Represents Course domain model.
 */
// todo mark as aggregate root
@Entity
public class Course implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    private String name;
    private String description;
    private PublishStatus publishStatus;
    private ApprovalStatus approvalStatus;
    private CourseRating rating;
    private NumberOfStudents numberOfStudents;

    @OneToOne
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Lecture> lectures;

    // for JPA
    private Course() {

    }

    Course(CreateCourseCommand command) {
        this.uuid = UUID.randomUUID();
        this.name = command.getName();
        this.description = command.getDescription();
        this.rating = new CourseRating(0);
        this.numberOfStudents = new NumberOfStudents(0);
        this.publishStatus = PublishStatus.DRAFT;
        this.approvalStatus = ApprovalStatus.NOT_SENT_FOR_APPROVAL;
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

    public void increaseNumberOfStudents() {
        numberOfStudents = new NumberOfStudents(numberOfStudents.getNumber() + 1);
    }

    // todo implement
    public void addLecture() {

    }

    // todo implement
    public void removeLecture() {

    }
}
