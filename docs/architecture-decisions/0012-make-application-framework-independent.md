# 12. Make Application Framework Independent
Date: 2025-09-24

## Status
Accepted

## Context
The current application is tightly coupled to Spring. This creates difficulties if we want to switch to another framework (e.g., Quarkus) or run parts of the system without Spring. To align with hexagonal architecture principles, the core domain should be independent from any framework.

## Decision
We will refactor the application to remove framework-specific details from the core modules. As a next step, we will attempt to integrate Quarkus as an alternative framework, verifying the framework independence of the core modules.