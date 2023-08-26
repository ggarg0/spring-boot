DROP TABLE IF EXISTS tutorials;

create table tutorials
( 
   id INT IDENTITY(10001, 1) PRIMARY KEY,
   title varchar(255) not null,
   description varchar(255),
   published INT
);