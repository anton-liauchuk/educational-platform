package com.educational.platform.administration.course.decline;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalDTO;
import com.educational.platform.administration.course.CourseProposalRepository;
import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.integration.events.CourseDeclinedByAdminIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;
import java.util.Optional;

/**
 * Command handler for {@link DeclineCourseProposalCommand} declines a course proposal.
 */
@RequiredArgsConstructor
@Component
public class DeclineCourseProposalCommandHandler {

    private final TransactionTemplate transactionTemplate;
    private final CourseProposalRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * Handles decline course proposal command. Declines and save declined course proposal
     *
     * @param command command
     * @throws ResourceNotFoundException if resource not found
     */
    public void handle(DeclineCourseProposalCommand command) {
        final CourseProposal proposal = transactionTemplate.execute(transactionStatus -> {
            final Optional<CourseProposal> dbResult = repository.findByUuid(command.getUuid());
            if (dbResult.isEmpty()) {
                throw new ResourceNotFoundException(String.format("Course Proposal with uuid: %s not found", command.getUuid()));
            }
            final CourseProposal courseProposal = dbResult.get();

            courseProposal.decline();
            repository.save(courseProposal);

            return courseProposal;
        });

        final CourseProposalDTO dto = Objects.requireNonNull(proposal).toDTO();
        eventPublisher.publishEvent(new CourseDeclinedByAdminIntegrationEvent(dto, dto.getUuid()));
    }

}
