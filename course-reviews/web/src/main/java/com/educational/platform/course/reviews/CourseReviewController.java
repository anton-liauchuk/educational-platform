package com.educational.platform.course.reviews;

import com.educational.platform.course.reviews.create.ReviewCourseCommand;
import com.educational.platform.course.reviews.create.ReviewCourseCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Represents Course Review Controller.
 */
@Validated
@RestController
@RequiredArgsConstructor
public class CourseReviewController {

    private final ReviewCourseCommandHandler handler;

    @PostMapping(value = "/courses/{uuid}/course-reviews", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CourseReviewCreatedResponse review(@PathVariable UUID uuid, @RequestBody @Valid ReviewCourseRequest request) {
        final ReviewCourseCommand command = ReviewCourseCommand.builder()
                .courseId(uuid)
                .reviewer(request.getReviewer())
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

        return new CourseReviewCreatedResponse(handler.handle(command));
    }
}
