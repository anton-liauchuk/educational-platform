package com.educational.platform.courses.course.query;

import java.util.List;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import com.educational.platform.courses.course.CourseLightDTO;
import com.educational.platform.courses.course.CourseRepository;

/**
 * Query handler for getting the list of courses.
 */
@Component
public class ListCourseQueryHandler {

	private final CourseRepository repository;

    public ListCourseQueryHandler(CourseRepository repository) {
        this.repository = repository;
    }

    /**
	 * Retrieves list of course dtos.
	 *
	 * @param query query.
	 * @return corresponding list of course dtos.
	 */
	@Nonnull
	public List<CourseLightDTO> handle(ListCourseQuery query) {
		return repository.list();
	}

}
