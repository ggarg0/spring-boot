INSERT INTO BOOK (NAME, AUTHOR, PRICE ) VALUES('SCALA BY XYZ', 'XYZ', 50.0);
INSERT INTO BOOK (NAME, AUTHOR, PRICE ) VALUES('JAVA BY SBA', 'SBA',20.0);
INSERT INTO BOOK (NAME, AUTHOR, PRICE ) VALUES('ANGULAR BY TRT', 'TRT',70.0);
INSERT INTO BOOK (NAME, AUTHOR, PRICE ) VALUES('NODE BY IYT', 'IYT',30.0);


INSERT INTO Tutorial (title, description, published ) VALUES('MBA Book', 'MBA', 'Yes');
INSERT INTO Tutorial (title, description, published ) VALUES('MCA Book', 'MCA','No');
INSERT INTO Tutorial (title, description, published ) VALUES('MS Book', 'MS','Yes');
INSERT INTO Tutorial (title, description, published ) VALUES('PHD Book', 'PHD','Yes');

INSERT INTO USERS (firstname, lastname, username, password, team, role, approved, active)
VALUES ('Adam', 'Sandler', 'adam@gmail.com', '$2a$10$uGibDOMvRoRYggPgRTvOX.dtqqb7sED2V5KJSuNEh6KbKMUcv7uka', 'Team A', 'Admin', 'Yes', 'Yes'),
	   ('Brad', 'Pitt', 'brad@gmail.com', '$2a$10$qFG8mxRGv1.xlUD19LvgceJqIsxqkfOPdt7c432yeeGzogTnFjt7i', 'Team B', 'Developer', 'Yes', 'Yes' ),
	   ('Allan', 'Waugh', 'allan@gmail.com', '$2a$10$4lYJ8iOFJl3VVJOR.ivqVesOsfwXDWKWavGNe9V4gOMVqBuIiBfkS', 'Team A', 'Developer', 'No', 'Yes'),
	   ('Chris', 'Dale', 'chris@gmail.com', '$2a$10$NI0EmLmkTqE9QbCzGXCtCu3Zg8iMIwrFL4eMYV58WkLD7fuNUGxNW', 'Team B', 'Developer', 'Yes', 'No' );
	   