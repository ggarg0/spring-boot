create table person
(
   id integer not null,
   name varchar(255) not null,
   location varchar(255),
   birthdate timestamp,
   primary key(id)
);

DROP TABLE IF EXISTS Book;
CREATE TABLE Book (           
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    author VARCHAR(250) NOT NULL,
    price FLOAT
);