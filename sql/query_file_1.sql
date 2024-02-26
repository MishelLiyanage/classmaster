    USE classdatabase;


    CREATE TABLE Account (
        id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        email VARCHAR(150) UNIQUE,
        password VARCHAR(150),
        first_name VARCHAR(100),
        last_name VARCHAR(100),
        display_name VARCHAR(100),
        role VARCHAR(20));
        
    CREATE TABLE Teacher(
        id INT NOT NULL  PRIMARY KEY,
        degree VARCHAR(200),
        description VARCHAR(200),
        contact_no VARCHAR(15),
        nic VARCHAR(15),
        FOREIGN KEY(id) REFERENCES Account(id));
        
    CREATE TABLE Staff(
        id INT NOT NULL PRIMARY KEY,
        nic VARCHAR(15),
        contact_no VARCHAR(15),
        FOREIGN KEY(id) REFERENCES Account(id));
        
    CREATE TABLE Student(
        id INT NOT NULL PRIMARY KEY,
        guardian_name VARCHAR(100),
        guardian_no VARCHAR(15),
        dob DATE,
        city VARCHAR(100),
        FOREIGN KEY(id) REFERENCES Account(id));
        
        
INSERT INTO Account (email, password, first_name, last_name, display_name, role) VALUES
    ('ashanchandrasiri1@gmail.com', 'ashan123','Ashan', 'Chandrasiri', 'AshChan', 'ADMIN'),
    ('bhagyafdo97@gmail.com', 'dinalie123','Dinalie', 'Fernando', 'DinDon', 'ADMIN'),
    ('mishelfdo@gmail.com', 'mishel234','Mishel', 'Fernando', 'MishF', 'STAFF'),
    ('samodha@gmail.com', 'samodha123','Samodha', 'Wijesooriya', 'samWije', 'STAFF'),
    ('sharukh@gmail.com', 'sharukh123','Sharuk', 'Emmanuel', 'SharkE', 'TEACHER');


INSERT INTO Staff (id, nic, contact_no) 
VALUES 
(3, '2002457896582', '0705489451');

INSERT INTO Teacher (id, nic, contact_no) 
VALUES 
(3, '2002457896582', '0705489451');

INSERT INTO `Teacher` (`id`, `degree`, `description`, `contact_no`, `nic`) 
VALUES (5, 'Bsc in Science', 'OL Science Teacher', '0778548963', '895685120V');


CREATE TABLE Course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    amount DOUBLE,
    teacherId INT,
    day VARCHAR(255),
    fromTime TIME,
    toTime TIME,
    FOREIGN KEY (teacherId) REFERENCES Teacher(id)
);

CREATE TABLE CourseAssignment (
    studentId INT,
    courseId INT,
    joinedDate DATE,
    complete BOOLEAN,
    PRIMARY KEY (studentId, courseId),
    FOREIGN KEY (studentId) REFERENCES Student(id),
    FOREIGN KEY (courseId) REFERENCES Course(id)
);

CREATE TABLE Attendance (
    student_id INT,
    course_id INT,
    attend_date DATE,
    attend_time TIME,
    PRIMARY KEY (student_id, course_id, attend_date),
    FOREIGN KEY (student_id) REFERENCES Student (id),
    FOREIGN KEY (course_id) REFERENCES Course (id)
);