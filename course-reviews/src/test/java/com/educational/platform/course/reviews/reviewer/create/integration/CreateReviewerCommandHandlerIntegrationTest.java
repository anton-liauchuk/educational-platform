package com.educational.platform.course.reviews.reviewer.create.integration;

import com.educational.platform.course.reviews.Reviewer;
import com.educational.platform.course.reviews.ReviewerRepository;
import com.educational.platform.course.reviews.reviewer.create.CreateReviewerCommand;
import com.educational.platform.course.reviews.reviewer.create.CreateReviewerCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CreateReviewerCommandHandlerIntegrationTest {

    @Autowired
    private ReviewerRepository repository;

    @SpyBean
    private CreateReviewerCommandHandler sut;

    @Test
    void handle_validCommand_reviewerSaved() {
        // given
        final CreateReviewerCommand command = new CreateReviewerCommand(15);

        // when
        sut.handle(command);

        // then
        final Optional<Reviewer> saved = repository.findOne(Example.of(new Reviewer(command)));
        assertThat(saved).isNotEmpty();
        final Reviewer reviewer = saved.get();
        assertThat(reviewer)
                .hasFieldOrPropertyWithValue("originalStudentId", 15);
    }
}
