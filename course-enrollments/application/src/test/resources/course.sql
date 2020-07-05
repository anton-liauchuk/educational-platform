DELETE FROM course_enrollment;
DELETE FROM enroll_course;
DELETE FROM student;

INSERT INTO student (username) VALUES ('student');
INSERT INTO enroll_course (uuid) VALUES ('123E4567E89B12D3A456426655440001');
