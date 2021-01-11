# 10. Integration events implementation by using Axon Framework.
Date: 2021-01-10

## Status
Accepted

## Context
In [0002-integration-events-implementation.md](0002-integration-events-implementation.md) was defined the solution for using Spring-event related classes for publishing and listening integration events. In current implementation of application we have Axon Framework which have rich tools for implementing such functionality. After migrating to Axon implementation of integration events, in future, we can enable event sourcing.

## Decision
Axon Framework will be used for integration events implementation.
