# 2. Integration events.
Date: 2020-03-04
Log date: 2020-03-04

## Status
Accepted

## Context
For implementing event-driven development, the platform should be available for communication with integration events.

## Decision
We will start from standard Spring Events classes: ApplicationListener, ApplicationEvent without dependency to external middleware component. We can add custom features to Spring functionality when it's needed.
todo: For now, all events will be stored in integration-events module. But this solution should reviewed.
