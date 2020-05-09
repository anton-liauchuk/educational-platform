package com.educational.platform.course.reviews.create.integration;

import com.educational.platform.course.reviews.*;
import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommand;
import com.educational.platform.course.reviews.create.ReviewCourseCommand;
import com.educational.platform.course.reviews.create.ReviewCourseCommandHandler;
import com.educational.platform.course.reviews.reviewer.create.CreateReviewerCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReviewCourseCommandHandlerIntegrationTest {

    @Autowired
    private CourseReviewRepository courseReviewRepository;

    @Autowired
    private ReviewableCourseRepository reviewableCourseRepository;

    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private CourseReviewFactory courseReviewFactory;

    @SpyBean
    private ReviewCourseCommandHandler sut;

    private UUID courseId;
    private String reviewerUsername;

    @BeforeEach
    void setUp() {
        courseId = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final ReviewableCourse reviewableCourse = new ReviewableCourse(new CreateReviewableCourseCommand(courseId));
        reviewableCourseRepository.save(reviewableCourse);

        reviewerUsername = "username";
        final Reviewer reviewer = new Reviewer(new CreateReviewerCommand(reviewerUsername));
        reviewerRepository.save(reviewer);
    }

    @Test
    void handle_validReviewCourseCommand_reviewSaved() {
        // given
        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(courseId)
                .reviewer(reviewerUsername)
                .rating(4.0)
                .comment("comment")
                .build();

        // when
        sut.handle(command);

        // then
        final Optional<CourseReview> saved = courseReviewRepository.findAll()
                .stream()
                .filter(courseReview -> new Comment("comment").equals(ReflectionTestUtils.getField(courseReview, "comment")))
                .findAny();
        assertThat(saved).isNotEmpty();
        final CourseReview review = saved.get();
        assertThat(review)
                .hasFieldOrPropertyWithValue("rating", new CourseRating(4))
                .hasFieldOrPropertyWithValue("comment", new Comment("comment"));
    }
}
