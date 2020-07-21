package com.educational.platform.course.reviews.query;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.educational.platform.course.reviews.CourseReviewDTO;
import com.educational.platform.course.reviews.CourseReviewRepository;

import lombok.RequiredArgsConstructor;

/**
 * Query handler for getting the course reviews by course uuid.
 */
@RequiredArgsConstructor
@Component
public class ListCourseReviewsByCourseUUIDQueryHandler {

	private final CourseReviewRepository repository;

	/**
	 * Retrieves course reviews for particular course uuid.
	 *
	 * @param query query.
	 * @return course reviews.
	 */
	@QueryHandler
	@NonNull
	public List<CourseReviewDTO> handle(ListCourseReviewsByCourseUUIDQuery query) {
		return repository.listCourseReviews(query.getUuid());
	}

}
