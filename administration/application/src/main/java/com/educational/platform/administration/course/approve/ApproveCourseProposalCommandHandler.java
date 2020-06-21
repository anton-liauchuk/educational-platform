package com.educational.platform.administration.course.approve;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalAlreadyApprovedException;
import com.educational.platform.administration.course.CourseProposalDTO;
import com.educational.platform.administration.course.CourseProposalRepository;
import com.educational.platform.administration.integration.event.CourseApprovedByAdminIntegrationEvent;
import com.educational.platform.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;
import java.util.Optional;

/**
 * Command handler for {@link ApproveCourseProposalCommand} approves a course proposal.
 */
@RequiredArgsConstructor
@Component
public class ApproveCourseProposalCommandHandler {

    private final TransactionTemplate transactionTemplate;
    private final CourseProposalRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * Handles approve course proposal command. Approves and save approved course proposal
     *
     * @param command command
     * @throws ResourceNotFoundException              if resource not found
     * @throws CourseProposalAlreadyApprovedException course proposal already approved
     */
    @PreAuthorize("hasRole('ADMIN')")
    public void handle(ApproveCourseProposalCommand command) {
        final CourseProposal proposal = transactionTemplate.execute(transactionStatus -> {
            final Optional<CourseProposal> dbResult = repository.findByUuid(command.getUuid());
            if (dbResult.isEmpty()) {
                throw new ResourceNotFoundException(String.format("Course Proposal with uuid: %s not found", command.getUuid()));
            }
            final CourseProposal courseProposal = dbResult.get();

            courseProposal.approve();
            repository.save(courseProposal);

            return courseProposal;
        });

        final CourseProposalDTO dto = Objects.requireNonNull(proposal).toDTO();
        eventPublisher.publishEvent(new CourseApprovedByAdminIntegrationEvent(dto, dto.getUuid()));
    }
}
