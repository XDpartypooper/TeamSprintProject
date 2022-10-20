DROP TABLE if exists Users;

-- CREATE TABLE users
CREATE TABLE Users  (
	UserName				VARCHAR(20)		NOT NULL,
	Password				VARCHAR(20)		NOT NULL,
	Email					VARCHAR(50)		NOT NULL,
	ProfileType 				VARCHAR(15)		NOT NULL,
	ID   		 			VARCHAR(4)		NOT NULL,

CONSTRAINT users_PKEY PRIMARY KEY (UserName, ID)
);

-- insert dummy data for the 4 profiles
INSERT INTO Users VALUES ('Roy','Password1','Bruh1@uow.com','4','001');
INSERT INTO Users VALUES ('Author','Password2','Bruh2@uow.com','1','002');
INSERT INTO Users VALUES ('ConChair','Password3','Bruh3@uow.com','2','003');
INSERT INTO Users VALUES ('Reviewer','Password4','Bruh4@uow.com','3','004');

select * from users; -- view table users

create database sprint;-- to create the db


