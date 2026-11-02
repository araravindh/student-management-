CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;
CREATE TABLE students(student_id INT PRIMARY KEY AUTO_INCREMENT, student_name VARCHAR(100) NOT NULL, email VARCHAR(120) NOT NULL UNIQUE, phone VARCHAR(15), age INT NOT NULL);
CREATE TABLE courses(course_id INT PRIMARY KEY AUTO_INCREMENT, course_name VARCHAR(100) NOT NULL UNIQUE, duration_months INT NOT NULL);
CREATE TABLE enrollments(enrollment_id INT PRIMARY KEY AUTO_INCREMENT, student_id INT NOT NULL, course_id INT NOT NULL, enrollment_date DATE NOT NULL, FOREIGN KEY(student_id) REFERENCES students(student_id), FOREIGN KEY(course_id) REFERENCES courses(course_id), UNIQUE(student_id,course_id));
CREATE TABLE attendance(attendance_id INT PRIMARY KEY AUTO_INCREMENT, student_id INT NOT NULL, course_id INT NOT NULL, attendance_date DATE NOT NULL, status ENUM('PRESENT','ABSENT') NOT NULL, FOREIGN KEY(student_id) REFERENCES students(student_id), FOREIGN KEY(course_id) REFERENCES courses(course_id), UNIQUE(student_id,course_id,attendance_date));
CREATE TABLE marks(mark_id INT PRIMARY KEY AUTO_INCREMENT, student_id INT NOT NULL, course_id INT NOT NULL, exam_name VARCHAR(80) NOT NULL, marks_obtained DECIMAL(5,2) NOT NULL, max_marks DECIMAL(5,2) NOT NULL, FOREIGN KEY(student_id) REFERENCES students(student_id), FOREIGN KEY(course_id) REFERENCES courses(course_id), UNIQUE(student_id,course_id,exam_name));
INSERT IGNORE INTO courses(course_name,duration_months) VALUES ('Java Programming',3),('Python Programming',3),('Web Development',4);
