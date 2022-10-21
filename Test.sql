create database sprint;-- to create the db

DROP TABLE if exists Users;
-- CREATE TABLE users
CREATE TABLE Users  (
	UserName				VARCHAR(20)		NOT NULL,
	Password				VARCHAR(20)		NOT NULL,
	Email					VARCHAR(50)		NOT NULL,
	ProfileType 				VARCHAR(30)		NOT NULL,
	ID   		 			VARCHAR(4)		NOT NULL,

CONSTRAINT users_PKEY PRIMARY KEY (UserName, ID)
);
-- insert dummy data for the 4 profiles
INSERT INTO Users VALUES ('Roy','Password1','Bruh1@uow.com','System Admin','001');
INSERT INTO Users VALUES ('Author','Password2','Bruh2@uow.com','Author','002');
INSERT INTO Users VALUES ('ConChair','Password3','Bruh3@uow.com','Conference Chair','003');
INSERT INTO Users VALUES ('Reviewer','Password4','Bruh4@uow.com','Reviwer','004');

DROP TABLE if exists usertype;

-- CREATE TABLE usertype
CREATE TABLE usertype  (
	ProfileType 				VARCHAR(30)		NOT NULL,
CONSTRAINT users_PKEY PRIMARY KEY (ProfileType)
);
-- insert dummy data for the 4 profiles
INSERT INTO usertype VALUES ('System Admin');
INSERT INTO usertype VALUES ('Author');
INSERT INTO usertype VALUES ('Conference Chair');
INSERT INTO usertype VALUES ('Reviwer');

select * from users; -- view table users
select * from usertype; 






--below is testing data for papers
CREATE TABLE Papers  (
	PaperName				VARCHAR(50)		NOT NULL,
	AuthorID   		 		VARCHAR(4)		NOT NULL, 
	allocatedID				VARCHAR(4),
	SubmittedDate			VARCHAR(20)		NOT NULL,
	
	--Email					VARCHAR(50)		NOT NULL,
	--ProfileType 			VARCHAR(15)		NOT NULL,
	
CONSTRAINT Papers_PKEY PRIMARY KEY (UserName, ID)
FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)

CONSTRAINT Papers_FKEY FOREIGN KEY (AuthorID) REFERENCES users(ID)
);
