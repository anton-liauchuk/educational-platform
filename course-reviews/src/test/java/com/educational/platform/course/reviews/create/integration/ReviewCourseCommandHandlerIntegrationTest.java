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
import org.springframework.data.domain.Example;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

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

    private Integer courseId;
    private Integer reviewerId;

    @BeforeEach
    void setUp() {
        final ReviewableCourse reviewableCourse = new ReviewableCourse(new CreateReviewableCourseCommand(44));
        reviewableCourseRepository.save(reviewableCourse);
        courseId = (Integer) ReflectionTestUtils.getField(reviewableCourse, "id");

        final Reviewer reviewer = new Reviewer(new CreateReviewerCommand(55));
        reviewerRepository.save(reviewer);
        reviewerId = (Integer) ReflectionTestUtils.getField(reviewableCourse, "id");
    }

    @Test
    void handle_validReviewCourseCommand_reviewSaved() {
        // given
        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(courseId)
                .reviewerId(reviewerId)
                .rating(4)
                .comment("comment")
                .build();

        // when
        sut.handle(command);

        // then
        final Optional<CourseReview> saved = courseReviewRepository.findOne(Example.of(courseReviewFactory.createFrom(command)));
        assertThat(saved).isNotEmpty();
        final CourseReview review = saved.get();
        assertThat(review)
                .hasFieldOrPropertyWithValue("rating", new CourseRating(4))
                .hasFieldOrPropertyWithValue("comment", new Comment("comment"));
    }
}
