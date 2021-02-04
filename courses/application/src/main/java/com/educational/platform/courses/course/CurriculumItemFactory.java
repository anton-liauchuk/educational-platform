package com.educational.platform.courses.course;

import javax.validation.ConstraintViolationException;

import com.educational.platform.courses.course.create.CreateCurriculumItemCommand;
import com.educational.platform.courses.course.create.CreateLectureCommand;
import com.educational.platform.courses.course.create.CreateQuizCommand;

/**
 * Represents Curriculum Item Factory.
 */
public class CurriculumItemFactory {

	/**
	 * Creates course from command.
	 *
	 * @param createCurriculumItemCommand course command
	 * @return course
	 * @throws ConstraintViolationException in the case of validation issues
	 */
	public static CurriculumItem createFrom(CreateCurriculumItemCommand createCurriculumItemCommand, Course course) {
		if (createCurriculumItemCommand instanceof CreateLectureCommand) {
			return new Lecture((CreateLectureCommand) createCurriculumItemCommand, createCurriculumItemCommand.getSerialNumber(), course);
		} else if (createCurriculumItemCommand instanceof CreateQuizCommand) {
			return new Quiz((CreateQuizCommand) createCurriculumItemCommand, createCurriculumItemCommand.getSerialNumber(), course);
		}

		return null;
	}

}
