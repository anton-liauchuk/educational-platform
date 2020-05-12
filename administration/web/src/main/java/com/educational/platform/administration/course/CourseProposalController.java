package com.educational.platform.administration.course;

import com.educational.platform.administration.course.approve.ApproveCourseProposalCommand;
import com.educational.platform.administration.course.approve.ApproveCourseProposalCommandHandler;
import com.educational.platform.administration.course.decline.DeclineCourseProposalCommand;
import com.educational.platform.administration.course.decline.DeclineCourseProposalCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final ApproveCourseProposalCommandHandler approveHandler;
    private final DeclineCourseProposalCommandHandler declineHandler;

    @PutMapping(value = "/{uuid}/approval-status", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approve(@PathVariable UUID uuid) {
        approveHandler.handle(new ApproveCourseProposalCommand(uuid));
    }

    @DeleteMapping(value = "/{uuid}/approval-status", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void decline(@PathVariable UUID uuid) {
        declineHandler.handle(new DeclineCourseProposalCommand(uuid));
    }

}
