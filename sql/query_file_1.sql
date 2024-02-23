CREATE TABLE Account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    displayName VARCHAR(255),
    role VARCHAR(255)
);

INSERT INTO Account (email, password, firstName, lastName, displayName, role) VALUES
('ashanchandrasiri1@gmail.com', 'ashan123', 'John', 'Doe', 'JohnDoe', 'ADMIN'),
('bhagyafdo97@gmail.com', 'dinalie123', 'Jane', 'Smith', 'JaneSmith', 'ADMIN'),
('michael@example.com', 'password789', 'Michael', 'Johnson', 'MichaelJ', 'USER'),
('emily@example.com', 'passwordabc', 'Emily', 'Williams', 'EmilyW', 'USER'),
('david@example.com', 'passworddef', 'David', 'Brown', 'DavidB', 'USER'),
('sarah@example.com', 'passwordghi', 'Sarah', 'Jones', 'SarahJ', 'USER'),
('matthew@example.com', 'passwordjkl', 'Matthew', 'Davis', 'MatthewD', 'USER'),
('olivia@example.com', 'passwordmno', 'Olivia', 'Miller', 'OliviaM', 'USER'),
('james@example.com', 'passwordpqr', 'James', 'Wilson', 'JamesW', 'USER'),
('emma@example.com', 'passwordstu', 'Emma', 'Moore', 'EmmaM', 'USER');