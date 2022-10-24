create database sprint;-- to create the db use when there is not sprint db

select * from users; -- view table users
select * from usertype; 
select * from papers; 
select * from Reviewer; 


-- view all reviewers
SELECT users.UserName,Reviewer.ReviewerID,Reviewer.WorkLoad
From Reviewer 
INNER JOIN users 
ON Reviewer.ReviewerID = users.ID;


-- run code from here
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
INSERT INTO Users VALUES ('Author2','Password2','Bruh5@uow.com','Author','005');

DROP TABLE if exists usertype;

-- CREATE TABLE usertype
CREATE TABLE usertype  (
	ProfileType 				VARCHAR(30)		NOT NULL,
	ProfileType_ID   		 	VARCHAR(4)		NOT NULL,
CONSTRAINT users_PKEY PRIMARY KEY (ProfileType,ProfileType_ID)
);
-- insert dummy data for the 4 profiles
INSERT INTO usertype VALUES ('System Admin','1');
INSERT INTO usertype VALUES ('Author','2');
INSERT INTO usertype VALUES ('Conference Chair','3');
INSERT INTO usertype VALUES ('Reviwer','4');

-- Papers 
DROP TABLE if exists Papers;
CREATE TABLE Papers  (
	PaperName				VARCHAR(100)	NOT NULL,
	AuthorID   		 		VARCHAR(4)		NOT NULL,
	co_AuthorID				VARCHAR(4),
	PaperID 				VARCHAR(4)      NOT NULL,
	ALReviewerID		    VARCHAR(4),
	
CONSTRAINT Papers_PKEY PRIMARY KEY (PaperName,PaperID,AuthorID)
);
-- Reviewer
DROP TABLE if exists Reviewer;
CREATE TABLE Reviewer  (
	ReviewerID   		 		VARCHAR(4)		NOT NULL, 
	WorkLoad				    VARCHAR(4),
CONSTRAINT Reviewer_PKEY PRIMARY KEY (ReviewerID)
);

-- dummy value for author 
INSERT INTO papers VALUES ('Lusty Argonian Maid Folio','002',null,'1', null);
INSERT INTO papers VALUES ('RoRos bizare adventure','005',null,'2', null);
INSERT INTO Reviewer VALUES ('004', null);
-- to here





-- testing stuff below

-- bids 
CREATE TABLE Reviewer  (
	PaperID     		 		VARCHAR(4)		NOT NULL, 
	Bids					    VARCHAR(10),
	BidderID     		 		VARCHAR(4),

CONSTRAINT Papers_PKEY PRIMARY KEY (PaperID)
);
-- comment
-- rating
-- reviews