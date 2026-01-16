# 13. Removal of Lombok Dependency
Date: 2026-01-09

## Status
Done

## Context
The codebase currently uses `Lombok` for reducing boilerplate code (getters, setters, builders, etc.). However, this introduces:
- Build tool compatibility issues during JDK upgrades
- Hidden complexity that complicates debugging
- Unnecessary third-party dependency for features now available in modern Java

## Decision
We will migrate away from `Lombok` dependency by replacing its annotations with standard Java language features and explicit code.
