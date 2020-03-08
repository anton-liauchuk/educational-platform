# 2. Communications between bounded contexts.
Date: 2020-02-29
Log date: 2020-02-29

## Status
Accepted

## Context
Some data should be available for several bounded contexts. The solution should be defined for retrieving the data from other bounded contexts.

todo
## Possible solutions
### 1. Direct method call

### 2. Event-driven

## Decision
Communication between bounded contexts asynchronous. Bounded contexts don't share data, it's forbidden to create a transaction which spans more than one bounded context.
- https://www.infoq.com/news/2014/11/sharing-data-bounded-contexts/
- http://www.kamilgrzybek.com/design/modular-monolith-primer/
- https://github.com/kgrzybek/modular-monolith-with-ddd#37-modules-integration

## Consequences
This solution reduces coupling of bounded contexts through data replication across contexts which results to higher bounded contexts independence.