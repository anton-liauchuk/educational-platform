delete from course;
delete from teacher;

INSERT INTO teacher (username) VALUES ('username');
INSERT INTO course (uuid, name, description, approval_status, number, rating) VALUES ('123E4567E89B12D3A456426655440001', 'course name', 'description', 'APPROVED', 0, 0);
UPDATE course SET teacher = (SELECT teacher.id FROM teacher WHERE teacher.username = 'username' GROUP BY teacher.id);
