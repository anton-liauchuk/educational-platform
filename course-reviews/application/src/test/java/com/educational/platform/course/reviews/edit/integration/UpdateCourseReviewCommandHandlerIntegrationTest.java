package com.educational.platform.course.reviews.edit.integration;

import com.educational.platform.course.reviews.*;
import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommand;
import com.educational.platform.course.reviews.create.ReviewCourseCommand;
import com.educational.platform.course.reviews.create.ReviewCourseCommandHandler;
import com.educational.platform.course.reviews.edit.UpdateCourseReviewCommand;
import com.educational.platform.course.reviews.edit.UpdateCourseReviewCommandHandler;
import com.educational.platform.course.reviews.reviewer.create.CreateReviewerCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UpdateCourseReviewCommandHandlerIntegrationTest {

    @Autowired
    private CourseReviewRepository courseReviewRepository;

    @Autowired
    private ReviewableCourseRepository reviewableCourseRepository;

    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private ReviewCourseCommandHandler reviewCourseCommandHandler;

    @SpyBean
    private UpdateCourseReviewCommandHandler sut;

    private UUID courseReviewId;

    @BeforeEach
    void setUp() {
        final UUID courseId = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final ReviewableCourse reviewableCourse = new ReviewableCourse(new CreateReviewableCourseCommand(courseId));
        reviewableCourseRepository.save(reviewableCourse);

        final String reviewerUsername = "reviewer";
        final Reviewer reviewer = new Reviewer(new CreateReviewerCommand(reviewerUsername));
        reviewerRepository.save(reviewer);

        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(courseId)
                .reviewer(reviewerUsername)
                .rating(4.0)
                .comment("comment")
                .build();
        reviewCourseCommandHandler.handle(command);

        final CourseReview review = courseReviewRepository.findAll().get(0);
        courseReviewId = (UUID) ReflectionTestUtils.getField(review, "uuid");
    }

    @Test
    @WithMockUser(username = "reviewer", roles = "STUDENT")
    void handle_validEditCourseReviewCommand_updatedReviewSaved() {
        // given
        final UpdateCourseReviewCommand command = UpdateCourseReviewCommand.builder()
                .uuid(courseReviewId)
                .rating(3.0)
                .comment("updated comment")
                .build();

        // when
        sut.handle(command);

        // then
        final CourseReview review = courseReviewRepository.findAll().get(0);
        assertThat(review)
                .hasFieldOrPropertyWithValue("rating", new CourseRating(3))
                .hasFieldOrPropertyWithValue("comment", new Comment("updated comment"));
    }
}
