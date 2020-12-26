# 1. Communications between bounded contexts.
Date: 2020-02-29

## Status
Accepted

## Context
Some common data should be used by several bounded contexts (modules, in the case of monolith application).

## Possible solutions
### 1. Direct method call
We will create the dependency between modules. After setting up the dependency, needed method can be called from another module.

### 2. Event-driven
The modules will not communicate by direct calls, so the dependency between modules is not needed. Common data can be duplicated inside the modules during executing the listener for particular events.

## Decision
Communication between bounded contexts asynchronous. Bounded contexts don't share data, it's forbidden to create a transaction which spans more than one bounded context.
- https://www.infoq.com/news/2014/11/sharing-data-bounded-contexts/
- http://www.kamilgrzybek.com/design/modular-monolith-primer/
- https://github.com/kgrzybek/modular-monolith-with-ddd#37-modules-integration

## Consequences
This solution reduces coupling of bounded contexts through data replication across contexts which results to higher bounded contexts independence.