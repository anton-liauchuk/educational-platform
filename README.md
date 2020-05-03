# Educational platform

## Modules

### Administration
To create a new `Course`, a `Teacher` needs to get approve from `Administrator`. Accepted `Course` is available for enrollment.

### Courses
A `Teacher` can submit for review new `Course`. This `Course` can be edited. A `Student` can view the list of `Course` and search by different parameters. The `Course` contains the list of `Lecture`.

### Course Enrollments
A `Student` can enroll `Course`. A `Lecture` can be marked as completed. `Student` can view the list of `Course Enrollment`. `Course Enrollment` can be archived.

### Course Reviews
A `Student` can create/edit feedback to enrolled `Course`. The list of `Course Review` are used for calculating the rating of `Course` and `Teacher`.

### Users
A `User` can be created after registration. `User` has the list of `Permission`. `User` can edit info in profile. `User` has role `Student` after registration. `User` can become a `Teacher`.

## Plan

| Feature | Status |
| ------- | ------ |
| Modular monolith with base functionality |  |
| Authentication/Security rules in application |  |
| API |  |
| Architecture tests |  |
| Integration events as public sub-module for each module |  |
| Extend functionality in modules |  |
| UI application |  |
| Microservices |  |
