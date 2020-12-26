# 7. API First.
Date: 2020-05-05

## Status
Accepted

## Context
API should have the documentation, we should define the process of delivering API and documentation.

## Possible solutions
### 1. API First
API First is one of engineering and architecture principles. In a nutshell API First requires two aspects:
- define APIs first, before coding its implementation, using a standard specification language;
- get early review feedback from peers and client developers;

### 2. Coding First
Another approach - coding first. In this solution, the documentation represented inside the code. And final version of documentation can be shared only after complete implementation of API.

## Decision
By defining APIs outside the code, we want to facilitate early review feedback and also a development discipline that focus service interface design on:
- profound understanding of the domain and required functionality
- generalized business entities / resources, i.e. avoidance of use case specific APIs
- clear separation of WHAT vs. HOW concerns, i.e. abstraction from implementation aspects — APIs should be stable even if we replace complete service implementation including its underlying technology stack

Useful links:
- https://opensource.zalando.com/restful-api-guidelines/#api-first
