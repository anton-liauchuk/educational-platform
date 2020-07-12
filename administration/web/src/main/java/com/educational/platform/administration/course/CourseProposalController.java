package com.educational.platform.administration.course;

import com.educational.platform.administration.course.approve.ApproveCourseProposalCommand;
import com.educational.platform.administration.course.decline.DeclineCourseProposalCommand;
import com.educational.platform.web.handler.ErrorResponse;
import lombok.RequiredArgsConstructor;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Represents Course Proposal Controller.
 */
@RequestMapping({"/administration/course-proposals"})
@RestController
@RequiredArgsConstructor
public class CourseProposalController {

    private final CommandGateway commandGateway;

    @PutMapping(value = "/{uuid}/approval-status", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approve(@PathVariable UUID uuid) {
        commandGateway.sendAndWait(new ApproveCourseProposalCommand(uuid));
    }

    @DeleteMapping(value = "/{uuid}/approval-status", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void decline(@PathVariable UUID uuid) {
        commandGateway.sendAndWait(new DeclineCourseProposalCommand(uuid));
    }

    @ExceptionHandler({CourseProposalAlreadyDeclinedException.class, CourseProposalAlreadyApprovedException.class})
    public ResponseEntity<ErrorResponse> onConflictException(Exception e) {
        final ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
