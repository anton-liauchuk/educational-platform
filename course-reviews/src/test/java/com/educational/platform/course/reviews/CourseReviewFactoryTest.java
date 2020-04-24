package com.educational.platform.course.reviews;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseReviewFactoryTest {

    private CourseReviewFactory sut;

    @BeforeEach
    void setUp() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        sut = new CourseReviewFactory(validator);
    }

    @Test
    void createFrom_validCourseReview_courseReviewCreated() {
        // given
        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(11)
                .studentId(12)
                .rating(4)
                .build();

        // when
        final CourseReview courseReview = sut.createFrom(command);

        // then
        assertThat(courseReview)
                .hasFieldOrPropertyWithValue("courseId", 11)
                .hasFieldOrPropertyWithValue("studentId", 12)
                .hasFieldOrPropertyWithValue("rating", new CourseRating(4));
    }


    @Test
    void createFrom_courseIdIsNull_constraintViolationException() {
        // given
        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(null)
                .studentId(12)
                .rating(4)
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
                .courseId(11)
                .studentId(null)
                .rating(4)
                .build();

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(ConstraintViolationException.class, createAction);
    }


    @ParameterizedTest
    @ValueSource(doubles = {-1, 6})
    void createFrom_ratingGt5_constraintViolationException(double rating) {
        // given
        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(11)
                .studentId(12)
                .rating(rating)
                .build();

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(ConstraintViolationException.class, createAction);
    }

}
