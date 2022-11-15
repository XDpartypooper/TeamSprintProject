
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
DROP TABLE if exists usertype;
-- CREATE TABLE usertype
CREATE TABLE usertype  (
	ProfileType 				VARCHAR(30)		NOT NULL,
	ProfileType_ID   		 	VARCHAR(4)		NOT NULL,
CONSTRAINT users_PKEY PRIMARY KEY (ProfileType,ProfileType_ID)
);
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
-- bids 
DROP TABLE if exists Bids;
CREATE TABLE Bids  (
	PaperID     		 		VARCHAR(4)		NOT NULL, 
	BidderID     		 		VARCHAR(4),
	Bid_status					int    			NOT NULL
);
-- reviews
DROP TABLE if exists Reviews;
CREATE TABLE Reviews  (
	PaperID     		 		VARCHAR(4)		NOT NULL, 
    ReviewerID   		 		VARCHAR(4)		, 
	Review						VARCHAR(500)	,
	Rating						int				, -- null means pending or deleted currently
    Review_status				int				,
CONSTRAINT Reviews_PKEY PRIMARY KEY (PaperID)
);

-- dummy data

-- insert dummy data for the 4 profiles
INSERT INTO usertype VALUES ('System Admin','1');
INSERT INTO usertype VALUES ('Author','2');
INSERT INTO usertype VALUES ('Conference Chair','3');
INSERT INTO usertype VALUES ('Reviewer','4');

-- insert dummy data for the 4 profiles
INSERT INTO Users VALUES ('Roy','Password1','Bruh1@uow.com','System Admin','001');
INSERT INTO Users VALUES ('Author','Password2','Bruh2@uow.com','Author','002');
INSERT INTO Users VALUES ('ConChair','Password3','Bruh3@uow.com','Conference Chair','003');
INSERT INTO Users VALUES ('Reviewer','Password4','Bruh4@uow.com','Reviewer','004');
INSERT INTO Users VALUES ('Author2','Password2','Bruh5@uow.com','Author','005');
INSERT INTO Users VALUES ('Reviewer2','Password4','Bruh4@uow.com','Reviewer','006');

-- author
INSERT INTO Users VALUES ('IkeEveland','Password2','Luxiem1@uow.com','Author','007');
INSERT INTO Users VALUES ('ShuYamino','Password2','Luxiem2@uow.com','Author','008');
INSERT INTO Users VALUES ('VoxAkuma','Password2','Luxiem3@uow.com','Author','009');
INSERT INTO Users VALUES ('LucaKaneshiro','Password2','Luxiem4@uow.com','Author','010');
INSERT INTO Users VALUES ('MystaRias','Password2','Luxiem5@uow.com','Author','011');
INSERT INTO Users VALUES ('EliraPendora','Password2','Lazulight1@uow.com','Author','012');
INSERT INTO Users VALUES ('PomuRainpuff','Password2','Lazulight2@uow.com','Author','013');
INSERT INTO Users VALUES ('FinanaRyugu','Password2','Lazulight3@uow.com','Author','014');
INSERT INTO Users VALUES ('SelenTatsuki','Password2','Obsydia1@uow.com','Author','015');
INSERT INTO Users VALUES ('RosemiLovelock','Password2','Obsydia2@uow.com','Author','016');
INSERT INTO Users VALUES ('PetraGurin','Password2','Obsydia3@uow.com','Author','017');
INSERT INTO Users VALUES ('ReimuEndou','Password2','Ethyria1@uow.com','Author','018');
INSERT INTO Users VALUES ('EnnaAlouette','Password2','Ethyria2@uow.com','Author','019');
INSERT INTO Users VALUES ('MillieParfait','Password2','Ethyria3@uow.com','Author','020');
INSERT INTO Users VALUES ('NinaKosaka','Password2','Ethyria4@uow.com','Author','021');

-- reviewer
INSERT INTO Users VALUES ('AlbanKnox','Password4','Noctyx1@uow.com','Reviewer','022');
INSERT INTO Users VALUES ('FulgurOvid','Password4','Noctyx2@uow.com','Reviewer','023');
INSERT INTO Users VALUES ('SonnyBrisko','Password4','Noctyx3@uow.com','Reviewer','024');
INSERT INTO Users VALUES ('YugoAsuma','Password4','Noctyx4@uow.com','Reviewer','025');
INSERT INTO Users VALUES ('UkiVioleta','Password4','Noctyx5@uow.com','Reviewer','026');
INSERT INTO Users VALUES ('MariaMarionette','Password4','Iluna1@uow.com','Reviewer','027');
INSERT INTO Users VALUES ('KyoKaneko','Password4','Iluna2@uow.com','Reviewer','028');
INSERT INTO Users VALUES ('AsterArcadia','Password4','Iluna3@uow.com','Reviewer','029');
INSERT INTO Users VALUES ('RenZotto','Password4','Iluna4@uow.com','Reviewer','030');
INSERT INTO Users VALUES ('ScarleYonaguni','Password4','Iluna5@uow.com','Reviewer','031');
INSERT INTO Users VALUES ('AiaAmare','Password4','Iluna6@uow.com','Reviewer','032');
INSERT INTO Users VALUES ('RegisAltare','Password4','Tempus1@uow.com','Reviewer','033');
INSERT INTO Users VALUES ('MagniDezmond','Password4','Tempus2@uow.com','Reviewer','034');
INSERT INTO Users VALUES ('AxelSyrios','Password4','Tempus3@uow.com','Reviewer','035');
INSERT INTO Users VALUES ('NoirVesper','Password4','Tempus4@uow.com','Reviewer','036');

-- conchair
INSERT INTO Users VALUES ('TakamiChika','Password3','Aqours1@uow.com','Conference Chair','037');
INSERT INTO Users VALUES ('SakurauchiRiko','Password3','Aqours2@uow.com','Conference Chair','038');
INSERT INTO Users VALUES ('WatanabeYou','Password3','Aqours3@uow.com','Conference Chair','039');
INSERT INTO Users VALUES ('TsushimaYoshiko','Password3','Aqours4@uow.com','Conference Chair','040');
INSERT INTO Users VALUES ('KunikidaHanamaru','Password3','Aqours5@uow.com','Conference Chair','041');
INSERT INTO Users VALUES ('KurosawaRuby','Password3','Aqours6@uow.com','Conference Chair','042');
INSERT INTO Users VALUES ('KurosawaDia','Password3','Aqours7@uow.com','Conference Chair','043');
INSERT INTO Users VALUES ('OharaMari','Password3','Aqours8@uow.com','Conference Chair','044');
INSERT INTO Users VALUES ('MatsuuraKanan','Password3','Aqours9@uow.com','Conference Chair','045');
INSERT INTO Users VALUES ('ShibuyaKanon','Password3','Liella1@uow.com','Conference Chair','046');
INSERT INTO Users VALUES ('TangKuku','Password3','Liella2@uow.com','Conference Chair','047');
INSERT INTO Users VALUES ('HeannaSumire','Password3','Liella3@uow.com','Conference Chair','048');
INSERT INTO Users VALUES ('HazukiRen','Password3','Liella4@uow.com','Conference Chair','049');
INSERT INTO Users VALUES ('ArashiChisato','Password3','Liella5@uow.com','Conference Chair','050');
INSERT INTO Users VALUES ('SakurakojiKinako','Password3','Liella6@uow.com','Conference Chair','051');
INSERT INTO Users VALUES ('YonemeMei','Password3','Liella7@uow.com','Conference Chair','052');
INSERT INTO Users VALUES ('WakanaShiki','Password3','Liella8@uow.com','Conference Chair','053');
INSERT INTO Users VALUES ('OnitsukaNatsumi','Password3','Liella9@uow.com','Conference Chair','054');

-- more
INSERT INTO Users VALUES ('UeharaAyumu','Password1','Nijigasaki1@uow.com','System Admin','055');
INSERT INTO Users VALUES ('YukiSetsuna','Password1','Nijigasaki2@uow.com','System Admin','056');
INSERT INTO Users VALUES ('MiyashitaAi','Password1','Nijigasaki3@uow.com','System Admin','057');
INSERT INTO Users VALUES ('KonoeKanata','Password1','Nijigasaki4@uow.com','System Admin','058');
INSERT INTO Users VALUES ('AsakaKarin','Password1','Nijigasaki5@uow.com','System Admin','059');
INSERT INTO Users VALUES ('EmmaVerde','Password1','Nijigasaki6@uow.com','System Admin','060');
INSERT INTO Users VALUES ('OsakaShizuku','Password1','Nijigasaki7@uow.com','System Admin','061');
INSERT INTO Users VALUES ('NakasuKasumi','Password1','Nijigasaki8@uow.com','System Admin','062');
INSERT INTO Users VALUES ('TennojiRina','Password1','Nijigasaki9@uow.com','System Admin','063');
INSERT INTO Users VALUES ('MifuneShioriko','Password1','Nijigasaki10@uow.com','System Admin','064');
INSERT INTO Users VALUES ('MiaTaylor','Password1','Nijigasaki11@uow.com','System Admin','065');
INSERT INTO Users VALUES ('ZhongLanzhu','Password1','Nijigasaki12@uow.com','System Admin','066');
INSERT INTO Users VALUES ('WeinMargarete','Password1','Liella10@uow.com','System Admin','067');
INSERT INTO Users VALUES ('FuraKanato','Password1','Voltaction1@uow.com','System Admin','068');
INSERT INTO Users VALUES ('WataraiHibari','Password1','Voltaction2@uow.com','System Admin','069');
INSERT INTO Users VALUES ('ShikinagiAkira','Password1','Voltaction3@uow.com','System Admin','070');
INSERT INTO Users VALUES ('SeraphDazzlegarden','Password1','Voltaction4@uow.com','System Admin','071');

-- +more author and reviewer 
INSERT INTO Users VALUES ('Alhaitham','Password2','Genshin1@uow.com','Author','072');
INSERT INTO Users VALUES ('Tartaglia','Password2','Genshin2@uow.com','Author','073');
INSERT INTO Users VALUES ('Yae Miko','Password2','Genshin3@uow.com','Author','074');
INSERT INTO Users VALUES ('Aether','Password2','Genshin4@uow.com','Author','075');
INSERT INTO Users VALUES ('Ganyu','Password2','Genshin5@uow.com','Author','076');
INSERT INTO Users VALUES ('Keqing','Password4','Genshin6@uow.com','Reviewer','077');
INSERT INTO Users VALUES ('Zhongli','Password4','Genshin7@uow.com','Reviewer','078');
INSERT INTO Users VALUES ('Kaveh','Password4','Genshin8@uow.com','Reviewer','079');
INSERT INTO Users VALUES ('Kokomi','Password4','Genshin9@uow.com','Reviewer','080');
INSERT INTO Users VALUES ('Shenhe','Password4','Genshin10@uow.com','Reviewer','081');
INSERT INTO Users VALUES ('Lumine','Password4','Genshin11@uow.com','Reviewer','082');
INSERT INTO Users VALUES ('Nahida','Password4','Genshin12@uow.com','Reviewer','083');

-- +more sysad and conchair
INSERT INTO Users VALUES ('Kevin','Password1','Mantis1@uow.com','System Admin','084');
INSERT INTO Users VALUES ('Elysia','Password1','Mantis2@uow.com','System Admin','085');
INSERT INTO Users VALUES ('Aponia','Password1','Mantis3@uow.com','System Admin','086');
INSERT INTO Users VALUES ('Eden','Password1','Mantis4@uow.com','System Admin','087');
INSERT INTO Users VALUES ('VillV','Password1','Mantis5@uow.com','System Admin','088');
INSERT INTO Users VALUES ('Kalpas','Password1','Mantis6@uow.com','System Admin','089');
INSERT INTO Users VALUES ('Su','Password1','Mantis7@uow.com','System Admin','090');
INSERT INTO Users VALUES ('Sakura','Password1','Mantis8@uow.com','System Admin','091');
INSERT INTO Users VALUES ('Kosma','Password1','Manti9@uow.com','System Admin','092');
INSERT INTO Users VALUES ('Mobius','Password1','Mantis10@uow.com','System Admin','093');
INSERT INTO Users VALUES ('Griseo','Password1','Mantis11@uow.com','System Admin','094');
INSERT INTO Users VALUES ('Hua','Password1','Mantis12@uow.com','System Admin','095');
INSERT INTO Users VALUES ('Felis','Password1','Mantis13@uow.com','System Admin','096');
INSERT INTO Users VALUES ('Bronya','Password3','Valkyria1@uow.com','Conference Chair','097');
INSERT INTO Users VALUES ('Seele','Password3','Valkyria2@uow.com','Conference Chair','098');
INSERT INTO Users VALUES ('Rozaliya','Password3','Valkyria3@uow.com','Conference Chair','099');
INSERT INTO Users VALUES ('Liliya','Password3','Valkyria4@uow.com','Conference Chair','100');
INSERT INTO Users VALUES ('Durandal','Password3','Valkyria5@uow.com','Conference Chair','101');
INSERT INTO Users VALUES ('Rita','Password3','Valkyria6@uow.com','Conference Chair','102');
INSERT INTO Users VALUES ('Theresa','Password3','Valkyria7@uow.com','Conference Chair','103');
INSERT INTO Users VALUES ('Kiana','Password3','Valkyria8@uow.com','Conference Chair','104');
INSERT INTO Users VALUES ('Mei','Password3','Valkyria9@uow.com','Conference Chair','105');

-- dummy data for reviewer work load 
INSERT INTO Reviewer VALUES ('004', null);
-- dummy value for author 
INSERT INTO papers VALUES ('Lusty Argonian Maid Folio','002',null,'1', null,null,'2011-11-11');
INSERT INTO papers VALUES ('RoRos bizare adventure','005',null,'2', null,null,'1998-09-09');
INSERT INTO papers VALUES ('Idoit guides to being a Conchair','002',null,'3', null,null,'2022-01-01');
INSERT INTO papers VALUES ('101 reasons why CSCI251 is horrible','005',null,'4', null,null,'2022-07-27');

INSERT INTO papers VALUES ('MAYDAY','007',null,'5',null,null,'2022-11-06');
INSERT INTO papers VALUES ('How to calculate banana surface area using calculus','008',null,'6',null,null,'2022-01-15');
INSERT INTO papers VALUES ('FNAF lore for beginner','009',null,'7',null,null,'2021-12-30');
INSERT INTO papers VALUES ('One Cheese Prinkles a day keeps the doctor away','010',null,'8',null,null,'2022-05-12');
INSERT INTO papers VALUES ('Bark','011',null,'9',null,null,'2022-01-01');
INSERT INTO papers VALUES ('Sheesh','012',null,'10',null,null,'2021-05-04');
INSERT INTO papers VALUES ('I quit my job for Minecraft','013',null,'11',null,null,'2022-12-25');
INSERT INTO papers VALUES ('Gamble with Gacha games','014',null,'12',null,null,'2021-08-15');
INSERT INTO papers VALUES ('My dog is not a cat','015',null,'13',null,null,'2000-10-10');
INSERT INTO papers VALUES ('I have no furniture','016',null,'14',null,null,'2008-11-16');
INSERT INTO papers VALUES ('Do Penguin Farts?','017',null,'15',null,null,'1998-07-04');
INSERT INTO papers VALUES ('83 Reasons why Apex is better than Valorant','018',null,'16',null,null,'2022-11-10');
INSERT INTO papers VALUES ('I hate people','019',null,'17',null,null,'2007-03-28');
INSERT INTO papers VALUES ('Indomie','020',null,'18',null,null,'2021-09-09');
INSERT INTO papers VALUES ('Being Non-Alcoholic is easy','021',null,'19',null,null,'1999-09-19');
INSERT INTO papers VALUES ('Difference between canned coke and bottled coke','072',null,'20',null,null,'2020-08-05');
INSERT INTO papers VALUES ('Best 50 Seafood Recipe','073',null,'21',null,null,'2020-07-12');
INSERT INTO papers VALUES ('Dark Knight Hero','074',null,'22',null,null,'2005-02-26');
INSERT INTO papers VALUES ('My Sister is gone for 500 years','075',null,'23',null,null,'2022-07-15');
INSERT INTO papers VALUES ('Time Management','076',null,'24',null,null,'2009-03-06');
-- dummy papers has no txt tag to it
-- to here




-- etc stuff 
create database sprint;

select * from users; -- view table users
select * from usertype; 
select * from papers; 
select * from Reviewer; 
select * from Reviews; 
select * from Bids; 

-- testing stuff below




