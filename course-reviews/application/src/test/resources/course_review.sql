DELETE FROM course_review;
DELETE FROM reviewer;
DELETE FROM reviewable_course;

INSERT INTO reviewer (username) VALUES ('reviewer');
INSERT INTO reviewable_course (original_course_id) VALUES ('123E4567E89B12D3A456426655440000');
INSERT INTO course_review (uuid, rating, comment) VALUES ('123E4567E89B12D3A456426655440001', 4, 'comment');
UPDATE course_review SET reviewer = (SELECT reviewer.id FROM reviewer WHERE reviewer.username = 'reviewer' GROUP BY reviewer.id);
UPDATE course_review SET course = (SELECT reviewable_course.id FROM reviewable_course WHERE reviewable_course.original_course_id = '123E4567E89B12D3A456426655440000' GROUP BY reviewable_course.id);

INSERT INTO reviewer (username) VALUES ('another-reviewer');
