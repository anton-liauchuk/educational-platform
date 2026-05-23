package com.educational.platform.administration.course.decline;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalAlreadyDeclinedException;
import com.educational.platform.administration.course.CourseProposalDTO;
import com.educational.platform.administration.course.CourseProposalRepository;
import com.educational.platform.administration.integration.event.CourseDeclinedByAdminIntegrationEvent;
import com.educational.platform.common.exception.ResourceNotFoundException;
import jakarta.inject.Named;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;
import java.util.Optional;

/**
 * Command handler for {@link DeclineCourseProposalCommand} declines a course proposal.
 */
@Named
public class DeclineCourseProposalCommandHandler {

    private final TransactionTemplate transactionTemplate;
    private final CourseProposalRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public DeclineCourseProposalCommandHandler(TransactionTemplate transactionTemplate, CourseProposalRepository repository, ApplicationEventPublisher eventPublisher) {
        this.transactionTemplate = transactionTemplate;
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Handles decline course proposal command. Declines and save declined course proposal
     *
     * @param command command
     * @throws ResourceNotFoundException              if resource not found
     * @throws CourseProposalAlreadyDeclinedException course proposal already declined
     */
    @PreAuthorize("hasRole('ADMIN')")
    public void handle(DeclineCourseProposalCommand command) {
        final CourseProposal proposal = transactionTemplate.execute(transactionStatus -> {
            final Optional<CourseProposal> dbResult = repository.findByUuid(command.uuid());
            if (dbResult.isEmpty()) {
                throw new ResourceNotFoundException(String.format("Course Proposal with uuid: %s not found", command.uuid()));
            }
            final CourseProposal courseProposal = dbResult.get();

            courseProposal.decline();
            repository.save(courseProposal);

            return courseProposal;
        });

        final CourseProposalDTO dto = Objects.requireNonNull(proposal).toDTO();
        eventPublisher.publishEvent(new CourseDeclinedByAdminIntegrationEvent(dto.uuid()));
    }

}
