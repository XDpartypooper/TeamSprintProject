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
INSERT INTO Users VALUES ('Reviewer','Password4','Bruh4@uow.com','Reviewer','004');
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
-- test for papers with file
DROP TABLE if exists Papers;
CREATE TABLE Papers  (
	PaperName				VARCHAR(100)	NOT NULL,
	AuthorID   		 		VARCHAR(4)		NOT NULL,
	co_AuthorID				VARCHAR(4),
	PaperID 				VARCHAR(4)      NOT NULL,
	ALReviewerID		    VARCHAR(4),
    Paper_File    			mediumblob  	,
	SubmitedDate			VARCHAR(10)		NOT NULL,
CONSTRAINT Papers_PKEY PRIMARY KEY (PaperName,PaperID,AuthorID)
);
-- Reviewer
DROP TABLE if exists Reviewer;
CREATE TABLE Reviewer  (
	ReviewerID   		 		VARCHAR(4)		NOT NULL, 
	WorkLoad				    VARCHAR(4),
CONSTRAINT Reviewer_PKEY PRIMARY KEY (ReviewerID)
);
INSERT INTO Reviewer VALUES ('004', null);
-- dummy value for author 
INSERT INTO papers VALUES ('Lusty Argonian Maid Folio','002',null,'1', '004',null,CURDATE());
INSERT INTO papers VALUES ('RoRos bizare adventure','005',null,'2', '004',null,CURDATE());
INSERT INTO papers VALUES ('Idoit guides to being a Conchair','002',null,'3', null,null,CURDATE());
INSERT INTO papers VALUES ('101 reasons why CSCI251 is horrible','005',null,'4', null,null,CURDATE());
-- dummy papers has no txt tag to it
-- bids 
DROP TABLE if exists Bids;
CREATE TABLE Bids  (
	PaperID     		 		VARCHAR(4)		NOT NULL, 
	BidderID     		 		VARCHAR(4),
	Bid_status					BOOL     NOT NULL,

CONSTRAINT bids_PKEY PRIMARY KEY (PaperID)
);
INSERT INTO Bids VALUES ('1' , "004" , false);

-- to here





-- etc stuff 
create database sprint;-- to create the db use when there is not sprint db

select * from users; -- view table users
select * from usertype; 
select * from papers; 
select * from Reviewer; 

select * from Bids; 
select * from Comment; 
select * from reviews;
 select * from Rating;
 
-- testing stuff below



-- comment
CREATE TABLE Comment  (
	PaperID     		 		VARCHAR(4)		NOT NULL, 

CONSTRAINT Comment_PKEY PRIMARY KEY (PaperID)
);
-- rating
CREATE TABLE Rating  (
	PaperID     		 		VARCHAR(4)		NOT NULL, 

CONSTRAINT Rating_PKEY PRIMARY KEY (PaperID)
);
-- reviews
CREATE TABLE reviews  (
	PaperID     		 		VARCHAR(4)		NOT NULL, 
CONSTRAINT Reviews_PKEY PRIMARY KEY (PaperID)
);