package com.educational.platform.administration.course.approve;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateCourseProposalCommand} creates a course proposal.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class CreateCourseProposalCommandHandler {

    private final CourseProposalRepository courseProposalRepository;

    /**
     * Creates course from command.
     *
     * @param command command
     */
    public void handle(CreateCourseProposalCommand command) {
        final CourseProposal courseProposal = new CourseProposal(command);
        courseProposalRepository.save(courseProposal);
    }

}
