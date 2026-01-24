package com.educational.platform.administration.course.create;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalRepository;

import jakarta.inject.Named;

import org.axonframework.messaging.commandhandling.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateCourseProposalCommand} creates a course proposal.
 */
@Named
@Transactional
public class CreateCourseProposalCommandHandler {

    private final CourseProposalRepository courseProposalRepository;

	public CreateCourseProposalCommandHandler(CourseProposalRepository courseProposalRepository) {
		this.courseProposalRepository = courseProposalRepository;
	}

	/**
     * Creates course proposal from command.
     *
     * @param command command
     */
    @CommandHandler
    public void handle(CreateCourseProposalCommand command) {
        final CourseProposal courseProposal = new CourseProposal(command);
        courseProposalRepository.save(courseProposal);
    }

}
