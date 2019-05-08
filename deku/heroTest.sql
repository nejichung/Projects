DROP DATABASE IF EXISTS HeroTest;
CREATE DATABASE HeroTest;
USE HeroTest;

CREATE TABLE Supers(
SuperID int PRIMARY KEY AUTO_INCREMENT,
`Name` varchar(30) NOT NULL,
`Description` varchar(50),
Quirk varchar(40));

CREATE TABLE Organizations(
OrganizationID int PRIMARY KEY AUTO_INCREMENT,
`Name` varchar(30),
`Description` varchar(50),
Address varchar(60),
PhoneNumber varchar(10),
EmailAddress varchar(30));

CREATE TABLE SupersOrganizations(
SuperID int,
OrganizationID int,

FOREIGN KEY (SuperID) REFERENCES Supers(SuperID),
FOREIGN KEY (OrganizationID) REFERENCES Organizations(OrganizationID));

CREATE TABLE Locations(
LocationID int PRIMARY KEY AUTO_INCREMENT,
LocationName varchar(30),
`Description` varchar(50),
Address varchar(60),
Latitude double,
Longitude double
);

CREATE TABLE Sightings(
SightingID int PRIMARY KEY AUTO_INCREMENT,
`Date` date,
LocationID int,
FOREIGN KEY (LocationID) REFERENCES Locations(LocationID));

CREATE TABLE SupersSightings(
SuperID int,
SightingID int,

FOREIGN KEY (SuperID) REFERENCES Supers(SuperID),
FOREIGN KEY (SightingID) REFERENCES Sightings(SightingID));

INSERT INTO Supers(SuperID, `Name`, `Description`, Quirk)
VALUES
('1','Deku','Methodical, Timid, and Quick Thinking','One For All'),
('2','Goku','Eats a lot','God'),
('3','Luffy','Rubber','Rubber'),
('4','Naruto','Never gives up','Kyuubi'),
('5','Ichigo','A million Transformations','Sword');

INSERT INTO Organizations(OrganizationID, `Name`, `Description`, Address, PhoneNumber, EmailAddress)
VALUES
('1','UA','#1 Academia','123 Yolo Street, Deez 42000','1234567890','allmight@ua.com'),
('2','Hidden Leaf Village','Hokage HYPE','777 Leaf Style, Leaf 2890','77724421','naruto@hiddenleaf.com'),
('3','Shinigami','Shinigami af','Death Ave, Sword 12305','12145590','strawberry@soul.com'),
('4','Pirates','RRRRR','DEEZ BOATS','1235612312','luffy@boat.com'),
('5','Krillin','We have a Krillin','EArt','12356899','krillin@earth.com');


INSERT INTO Locations(LocationID, LocationName, `Description`, Address, Latitude, Longitude)
VALUES
('1','Forest of Death','Very cool','123 Yolo Street, Deez 42000','57.75','-21.23'),
('2','DragonBALLZ','Slow af','753 Goku Street, Earth 12345',' 60.50','-123.46');

INSERT INTO Sightings (SightingID, `Date`, LocationID)
VALUES
('1','2000-02-02','1'),
('2','2010-07-07','2');
INSERT INTO SupersOrganizations (SuperID, OrganizationID)
VALUES
('1','1'),
('3','4'),
('4','2'),
('2','5'),
('5','3'),
('1','2');

INSERT INTO SupersSightings (SuperID, SightingID)
VALUES
('1','1'),
('2','2'),
('3','1'),
('4','1'),
('5','2'),
('1','2');