package com.educational.platform.course.reviews.course.create.integration;

import com.educational.platform.course.reviews.ReviewableCourse;
import com.educational.platform.course.reviews.ReviewableCourseRepository;
import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommand;
import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CreateReviewableCourseCommandHandlerIntegrationTest {

    @Autowired
    private ReviewableCourseRepository repository;

    @SpyBean
    private CreateReviewableCourseCommandHandler sut;

    @Test
    void handle_validCommand_reviewableCourseSaved() {
        // given
        final CreateReviewableCourseCommand command = new CreateReviewableCourseCommand(15);

        // when
        sut.handle(command);

        // then
        final Optional<ReviewableCourse> saved = repository.findOne(Example.of(new ReviewableCourse(command)));
        assertThat(saved).isNotEmpty();
        final ReviewableCourse reviewableCourse = saved.get();
        assertThat(reviewableCourse).hasFieldOrPropertyWithValue("originalCourseId", 15);
    }
}
