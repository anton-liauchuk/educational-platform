package com.educational.platform.course.reviews;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.axonframework.messaging.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.queryhandling.gateway.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educational.platform.course.reviews.create.ReviewCourseCommand;
import com.educational.platform.course.reviews.edit.UpdateCourseReviewCommand;
import com.educational.platform.course.reviews.query.ListCourseReviewsByCourseUUIDQuery;

/**
 * Represents Course Review Controller.
 */
@Validated
@RestController
public class CourseReviewController {

	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;

    public CourseReviewController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping(value = "/courses/{uuid}/reviews", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CourseReviewCreatedResponse review(@PathVariable("uuid") UUID uuid, @RequestBody @Valid ReviewCourseRequest request) {
		final ReviewCourseCommand command = new ReviewCourseCommand(uuid, request.rating(), request.comment());

		return new CourseReviewCreatedResponse(commandGateway.sendAndWait(command, UUID.class));
	}

	@GetMapping(value = "/courses/{uuid}/reviews", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<CourseReviewDTO> reviews(@PathVariable("uuid") UUID uuid) {
		final ListCourseReviewsByCourseUUIDQuery query = new ListCourseReviewsByCourseUUIDQuery(uuid);

		return queryGateway.queryMany(query, CourseReviewDTO.class).join();
	}

	@PutMapping(value = "/courses/{courseUuid}/reviews/{reviewUuid}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateReview(@PathVariable("courseUuid") UUID courseUuid, @PathVariable("reviewUuid") UUID reviewUuid, @RequestBody @Valid UpdateCourseReviewRequest request) {
		final UpdateCourseReviewCommand command = new UpdateCourseReviewCommand(reviewUuid, request.rating(), request.comment());

		commandGateway.sendAndWait(command);
	}
}
