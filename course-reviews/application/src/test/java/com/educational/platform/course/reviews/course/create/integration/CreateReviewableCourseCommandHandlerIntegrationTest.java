package com.educational.platform.course.reviews.course.create.integration;

import com.educational.platform.course.reviews.course.ReviewableCourse;
import com.educational.platform.course.reviews.course.ReviewableCourseRepository;
import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommand;
import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;
import java.util.UUID;

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
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final CreateReviewableCourseCommand command = new CreateReviewableCourseCommand(uuid);

        // when
        sut.handle(command);

        // then
        final Optional<ReviewableCourse> saved = repository.findByOriginalCourseId(uuid);
        assertThat(saved).isNotEmpty();
        final ReviewableCourse reviewableCourse = saved.get();
        assertThat(reviewableCourse).hasFieldOrPropertyWithValue("originalCourseId", uuid);
    }
}
