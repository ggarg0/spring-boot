DROP TABLE IF EXISTS Book;
CREATE TABLE Book (           
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    author VARCHAR(250) NOT NULL,
    price FLOAT
);

DROP TABLE IF EXISTS Tutorial;
CREATE TABLE Tutorial (           
	id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    description VARCHAR(250) NOT NULL,
    published VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
               userid INT AUTO_INCREMENT  PRIMARY KEY,
               firstname VARCHAR(100) NOT NULL,
               lastname VARCHAR(100) NOT NULL,
               username VARCHAR(100) NOT NULL,
               password VARCHAR(250) DEFAULT NULL,
               team VARCHAR(100),
               role VARCHAR(100),
               approved VARCHAR(20),
               active VARCHAR(20)                              
);

ALTER TABLE USERS ADD CONSTRAINT username_unique UNIQUE(username);