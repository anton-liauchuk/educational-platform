package com.educational.platform.course.reviews;


import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommand;
import com.educational.platform.course.reviews.create.ReviewCourseCommand;
import com.educational.platform.course.reviews.reviewer.create.CreateReviewerCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseReviewFactoryTest {

    @Mock
    private ReviewableCourseRepository reviewableCourseRepository;

    @Mock
    private ReviewerRepository reviewerRepository;

    private CourseReviewFactory sut;

    @BeforeEach
    void setUp() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        sut = new CourseReviewFactory(validator, reviewableCourseRepository, reviewerRepository);
    }

    @Test
    void createFrom_validCourseReview_courseReviewCreated() {
        // given
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(uuid)
                .reviewer("username")
                .rating(4)
                .comment("comment")
                .build();

        final CreateReviewableCourseCommand createCourseProposalCommand = new CreateReviewableCourseCommand(uuid);
        final ReviewableCourse correspondingReviewableCourse = new ReviewableCourse(createCourseProposalCommand);
        ReflectionTestUtils.setField(correspondingReviewableCourse, "id", 11);
        ReflectionTestUtils.setField(correspondingReviewableCourse, "originalCourseId", uuid);
        when(reviewableCourseRepository.findByOriginalCourseId(uuid)).thenReturn(Optional.of(correspondingReviewableCourse));

        final CreateReviewerCommand createReviewerCommand = new CreateReviewerCommand("username");
        final Reviewer correspondingReviewer = new Reviewer(createReviewerCommand);
        ReflectionTestUtils.setField(correspondingReviewer, "id", 22);
        ReflectionTestUtils.setField(correspondingReviewer, "username", "username");
        when(reviewerRepository.findByUsername("username")).thenReturn(Optional.of(correspondingReviewer));

        // when
        final CourseReview courseReview = sut.createFrom(command);

        // then
        // todo recheck course and reviewer references
        assertThat(courseReview)
                .hasFieldOrPropertyWithValue("rating", new CourseRating(4))
                .hasFieldOrPropertyWithValue("comment", new Comment("comment"));
    }


    @Test
    void createFrom_courseIdIsNull_constraintViolationException() {
        // given
        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(null)
                .reviewer("username")
                .rating(4)
                .comment("comment")
                .build();

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(ConstraintViolationException.class, createAction);
    }


    @Test
    void createFrom_studentIdIsNull_constraintViolationException() {
        // given
        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .reviewer(null)
                .rating(4)
                .comment("comment")
                .build();

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(ConstraintViolationException.class, createAction);
    }


    @ParameterizedTest
    @ValueSource(doubles = {-1, 6})
    void createFrom_invalidRating_constraintViolationException(double rating) {
        // given
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(uuid)
                .reviewer("username")
                .rating(rating)
                .comment("comment")
                .build();

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(ConstraintViolationException.class, createAction);
    }

}
