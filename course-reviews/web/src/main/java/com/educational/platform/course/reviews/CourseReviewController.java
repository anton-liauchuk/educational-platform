package com.educational.platform.course.reviews;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
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

import lombok.RequiredArgsConstructor;

/**
 * Represents Course Review Controller.
 */
@Validated
@RestController
@RequiredArgsConstructor
public class CourseReviewController {

	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;

	@PostMapping(value = "/courses/{uuid}/reviews", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CourseReviewCreatedResponse review(@PathVariable UUID uuid, @RequestBody @Valid ReviewCourseRequest request) {
		final ReviewCourseCommand command = ReviewCourseCommand
				.builder()
				.courseId(uuid)
				.rating(request.getRating())
				.comment(request.getComment())
				.build();

		return new CourseReviewCreatedResponse(commandGateway.sendAndWait(command));
	}

	@GetMapping(value = "/courses/{uuid}/reviews", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<CourseReviewDTO> reviews(@PathVariable UUID uuid) {
		final ListCourseReviewsByCourseUUIDQuery query = new ListCourseReviewsByCourseUUIDQuery(uuid);

		return queryGateway.query(query, ResponseTypes.multipleInstancesOf(CourseReviewDTO.class)).join();
	}

	@PutMapping(value = "/courses/{courseUuid}/reviews/{reviewUuid}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateReview(@PathVariable UUID courseUuid, @PathVariable UUID reviewUuid, @RequestBody @Valid UpdateCourseReviewRequest request) {
		final UpdateCourseReviewCommand command = UpdateCourseReviewCommand
				.builder()
				.uuid(reviewUuid)
				.rating(request.getRating())
				.comment(request.getComment())
				.build();

		commandGateway.sendAndWait(command);
	}
}
