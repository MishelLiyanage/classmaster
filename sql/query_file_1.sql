    USE classdatabase;


    CREATE TABLE Account (
        id INT NOT NULL PRIMARY KEY,
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
        
        
    INSERT INTO Account (id, email, password, first_name, last_name, display_name, role) VALUES
    (1, 'ashanchandrasiri1@gmail.com', 'ashan123','Ashan', 'Chandrasiri', 'AshChan', 'ADMIN'),
    (2, 'bhagyafdo97@gmail.com', 'dinalie123','Dinalie', 'Fernando', 'DinDon', 'ADMIN'),
    (3, 'mishelfdo@gmail.com', 'mishel234','Mishel', 'Fernando', 'MishF', 'STAFF'),
    (4, 'samodha@gmail.com', 'samodha123','Samodha', 'Wijesooriya', 'samWije', 'STAFF'),
    (5, 'sharukh@gmail.com', 'sharukh123','Sharuk', 'Emmanuel', 'SharkE', 'TEACHER'),
    (6, 'sarah@example.com', 'sarah123','Sarah', 'Jones', 'SarahJ', 'USER'),
    (7, 'matthew@example.com', 'matthew123','Matthew', 'Davis', 'MatthewD', 'USER'),
    (8, 'olivia@example.com', 'olivia123','Olivia', 'Miller', 'OliviaM', 'USER'),
    (9, 'james@example.com', 'james123','James', 'Wilson', 'JamesW', 'USER'),
    (10, 'emma@example.com', 'emma123','Emma', 'Moore', 'EmmaM', 'USER');


INSERT INTO Staff (id, nic, contact_no) 
VALUES 
(3, '2002457896582', '0705489451');
