DELETE FROM course_review;
DELETE FROM reviewable_course;
DELETE FROM reviewer;

INSERT INTO reviewable_course (original_course_id) VALUES ('123E4567E89B12D3A456426655440001');
INSERT INTO reviewer (username) VALUES ('username');
