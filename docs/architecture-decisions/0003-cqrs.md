# 3. CQRS.
Date: 2020-03-22

## Status
Accepted

## Context
There are possible solutions for writing and reading operations:
- use the same model;
- use separate models;

## Decision
CQRS principle will be used. It gives the flexibility in optimizing model for read and write operations. The simple version of CQRS is implemented in this application. On write operations, full logic is executed via aggregate. On read operations, DTO objects are created via JPQL queries on repository level.
- https://dzone.com/articles/cqrs-understanding-from-first-principles
- https://stackoverflow.com/questions/24474859/what-is-the-difference-between-command-commandhandler-and-service
