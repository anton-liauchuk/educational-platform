# 5. Identifier between modules.
Date: 2020-04-28

## Status
Accepted

## Context
During communications between bounded contexts, global identifiers are needed. Also, these identifiers are needed for integrating with external systems.

## Decision
Natural keys or uuids should be used. Primary keys are forbidden for communications between modules or with external systems. If entity has good natural key - it's the most preferable choice for identifier between modules.
- https://tomharrisonjr.com/uuid-or-guid-as-primary-keys-be-careful-7b2aa3dcb439
