package com.educational.platform.administration.course;

import com.educational.platform.administration.course.approve.ApproveCourseProposalCommand;
import com.educational.platform.administration.course.approve.ApproveCourseProposalCommandHandler;
import com.educational.platform.administration.course.decline.DeclineCourseProposalCommand;
import com.educational.platform.administration.course.decline.DeclineCourseProposalCommandHandler;
import com.educational.platform.administration.course.query.ListCourseProposalsQuery;
import com.educational.platform.administration.course.query.ListCourseProposalsQueryHandler;
import com.educational.platform.web.handler.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Represents Course Proposal Controller.
 */
@RequestMapping({"/administration/course-proposals"})
@RestController
public class CourseProposalController {

    private final ApproveCourseProposalCommandHandler approveCourseProposalCommandHandler;
    private final DeclineCourseProposalCommandHandler declineCourseProposalCommandHandler;
    private final ListCourseProposalsQueryHandler listCourseProposalsQueryHandler;

    public CourseProposalController(ApproveCourseProposalCommandHandler approveCourseProposalCommandHandler, DeclineCourseProposalCommandHandler declineCourseProposalCommandHandler, ListCourseProposalsQueryHandler listCourseProposalsQueryHandler) {
        this.approveCourseProposalCommandHandler = approveCourseProposalCommandHandler;
        this.declineCourseProposalCommandHandler = declineCourseProposalCommandHandler;
        this.listCourseProposalsQueryHandler = listCourseProposalsQueryHandler;
    }

    @PutMapping(value = "/{uuid}/approval-status", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approve(@PathVariable("uuid") UUID uuid) {
        approveCourseProposalCommandHandler.handle(new ApproveCourseProposalCommand(uuid));
    }

    @DeleteMapping(value = "/{uuid}/approval-status", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void decline(@PathVariable("uuid") UUID uuid) {
        declineCourseProposalCommandHandler.handle(new DeclineCourseProposalCommand(uuid));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseProposalDTO> courseProposals() {
        return listCourseProposalsQueryHandler.handle(new ListCourseProposalsQuery());
    }

    @ExceptionHandler({CourseProposalAlreadyDeclinedException.class, CourseProposalAlreadyApprovedException.class})
    public ResponseEntity<ErrorResponse> onConflictException(Exception e) {
        final ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
