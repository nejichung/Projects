DROP DATABASE IF EXISTS Pokemon;
CREATE DATABASE Pokemon;
USE Pokemon;

CREATE TABLE Moves (
MoveID INT PRIMARY KEY AUTO_INCREMENT,
MoveName varchar(20) NOT NULL,
Damage INT,
Accuracy INT,
PowerPoints INT);

CREATE TABLE Pokemons (
PokemonID INT PRIMARY KEY AUTO_INCREMENT,
`Name` varchar(20) NOT NULL,
Gender varchar(10) NOT NULL,
Description varchar(50) NOT NULL,
Nature varchar(20) NOT NULL,
Level INT,
BaseHealthPoints INT,
BaseAttack INT,
BaseDefense INT,
BaseSpecialAttack INT, 
BaseSpecialDefense INT,
BaseSpeed INT,
HealthPointEVS INT,
AttackEVS INT,
DefenseEVS INT,
SpecialAttackEVS INT,
SpecialDefenseEVS INT,
SpeedEVS INT,
HealthPointIVS INT,
AttackIVS INT,
DefenseIVS INT,
SpecialAttackIVS INT,
SpecialDefenseIVS INT,
SpeedIVS INT,
HealthPoints INT,
Attack INT,
Defense INT, 
SpecialAttack INT,
SpecialDefense INT,
Speed INT);

CREATE TABLE PokemonsMoves(
PokemonID INT,
MoveID INT,
FOREIGN KEY (PokemonID) references Pokemons(PokemonID),
FOREIGN KEY (MoveID) references Moves(MoveID));

CREATE TABLE Trainers (
TrainerID INT PRIMARY KEY AUTO_INCREMENT,
`Name` varchar(20) NOT NULL);

CREATE TABLE TrainersPokemons(
TrainerID INT,
PokemonID INT,
FOREIGN KEY (TrainerID) references Trainers(TrainerID),
FOREIGN KEY (PokemonID) references Pokemons(PokemonID));

CREATE TABLE Items (
ItemID INT PRIMARY KEY AUTO_INCREMENT,
ItemName varchar(20) NOT NULL,
Description varchar(100) NOT NULL);

CREATE TABLE TrainersItems(
TrainerID INT,
ItemID INT,
FOREIGN KEY (TrainerID) references Trainers(TrainerID),
FOREIGN KEY (ItemID) references Items(ItemID));

INSERT INTO Pokemons(PokemonID, `Name`, Gender, Description, Nature, Level, BaseHealthPoints, BaseAttack, BaseDefense, BaseSpecialAttack, BaseSpecialDefense, BaseSpeed, HealthPointEVS,
AttackEVS, DefenseEVS, SpecialAttackEVS, SpecialDefenseEVS, SpeedEVS, HealthPointIVS, AttackIVS, DefenseIVS, SpecialAttackIVS, SpecialDefenseIVS, SpeedIVS, HealthPoints, Attack, Defense, SpecialAttack, SpecialDefense, Speed)
VALUES
('1','Snorlax','Male','Best Pokemon Ever','Impish','100','160','110','65','65','110','30','252','4','252','0','0','0','31','31','31','31','31','31','524','257','251','149','256','96');

INSERT INTO Moves(MoveID, MoveName, Damage, Accuracy, PowerPoints)
VALUES
('1','Body Slam','85','100','15'),
('2',"Dragon's Ascent",'120','100','5'),
('3','Precipice Blades','120','85','5'),
('4','Origin Pulse','110','85','5');

INSERT INTO PokemonsMoves(PokemonID, MoveID)
VALUES
('1','1'),
('1','2'),
('1','3'),
('1','4');

INSERT INTO Trainers(TrainerID, `Name`)
VALUES
('1','Jacob');

INSERT INTO Items(ItemID, ItemName, Description)
VALUES
('1','Full Restore','Heal selected Pokemon to full health and all removes status effects.');

INSERT INTO TrainersPokemons(TrainerID, PokemonID)
VALUES
('1','1');

INSERT INTO TrainersItems(TrainerID, ItemID)
VALUES
('1','1');
