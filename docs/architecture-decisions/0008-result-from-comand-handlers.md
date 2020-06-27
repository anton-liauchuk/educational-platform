# 8. Results from command handlers.
Date: 2020-05-14

## Status
Accepted

## Context
The idea from CQRS - do not return anything from command processing. But in some cases, we need to get generated identifiers of new created resources.

## Decision
Command handlers can return generated identifiers after processing if it's needed.
