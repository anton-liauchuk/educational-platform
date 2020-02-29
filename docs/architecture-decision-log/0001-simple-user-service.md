# 1. Simple user service implementation
Date: 2020-02-25
Log date: 2020-02-26

## Status
Accepted

## Context
User service implementation is blocker for all other services.

## Decision
Provide simple user service implementation with JWT token. After implementation main features in modules, user service should be refactored by using OAuth2.

## Consequences
- other modules are available for implementation.