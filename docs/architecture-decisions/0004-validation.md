# 4. Validation.
Date: 2020-03-25

## Status
Accepted

## Context
There are possible solutions related to validation topic:
- always valid approach. Domain model is encapsulated, all requests are validated before executing logic in domain model;
- deferred validation. Write all data to domain model from request and validate everything in domain model;
- return validation object.

## Decision
Always valid approach will be used. So domain model will be changed from one valid state to another valid state. Technically, validation rules are defined on `Command` models and executed during processing the command. Javax validation-api is used for defining the validation rules via annotations.

Useful links:
- https://danielwhittaker.me/2016/04/20/how-to-validate-commands-in-a-cqrs-application/
- https://enterprisecraftsmanship.com/posts/validate-commands-cqrs/
- https://enterprisecraftsmanship.com/posts/validation-and-ddd/
- https://enterprisecraftsmanship.com/posts/always-valid-vs-not-always-valid-domain-model/
- http://www.kamilgrzybek.com/design/domain-model-validation/
- http://www.kamilgrzybek.com/design/rest-api-data-validation/
