DROP DATABASE IF EXISTS Pokemon;
CREATE DATABASE Pokemon;
USE Pokemon;

CREATE TABLE Moves (
MoveID INT PRIMARY KEY AUTO_INCREMENT,
MoveName varchar(20) NOT NULL,
Type varchar(20) NOT NULL,
Category varchar(20) NOT NULL,
Damage INT,
Accuracy INT,
PowerPoints INT);

CREATE TABLE Natures(
NatureID INT PRIMARY KEY AUTO_INCREMENT,
NatureName varchar(20) NOT NULL,
BlueStat varchar(20),
RedStat varchar(20));

CREATE TABLE Trainers (
TrainerID INT PRIMARY KEY AUTO_INCREMENT,
`Name` varchar(20) NOT NULL);

CREATE TABLE Pokemons (
PokemonID INT PRIMARY KEY AUTO_INCREMENT,
PokemonName varchar(20) NOT NULL,
Gender varchar(10) NOT NULL,
Description varchar(50) NOT NULL,
NatureID INT NOT NULL,
Level INT NOT NULL,
BaseHealthPoints INT NOT NULL,
BaseAttack INT NOT NULL,
BaseDefense INT NOT NULL,
BaseSpecialAttack INT NOT NULL, 
BaseSpecialDefense INT NOT NULL,
BaseSpeed INT NOT NULL,
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
Speed INT,
TrainerID INT,
FOREIGN KEY(TrainerID) REFERENCES Trainers(TrainerID),
FOREIGN KEY (NatureID) REFERENCES Natures(NatureID));

CREATE TABLE PokemonsMoves(
PokemonID INT,
MoveID INT,
FOREIGN KEY (PokemonID) references Pokemons(PokemonID),
FOREIGN KEY (MoveID) references Moves(MoveID));

CREATE TABLE Items (
ItemID INT PRIMARY KEY AUTO_INCREMENT,
ItemName varchar(20) NOT NULL,
Description varchar(100) NOT NULL,
Quantity INT);

CREATE TABLE TrainersItems(
TrainerID INT,
ItemID INT,
FOREIGN KEY (TrainerID) references Trainers(TrainerID),
FOREIGN KEY (ItemID) references Items(ItemID));

INSERT INTO Natures(NatureID, NatureName, BlueStat, RedStat)
VALUES
('1','Adamant','Attack','SpecialAttack'),
('2', 'Bashful', 'SpecialAttack', 'SpecialAttack'),
('3','Bold','Defense','Attack'),
('4','Brave','Attack','Speed'),
('5','Calm','SpecialDefense','Attack'),
('6','Careful','SpecialDefense','SpecialAttack'),
('7','Docile', 'Defense', 'Defense'),
('8','Gentle','SpecialDefense','Defense'),
('9','Hardy', 'Attack', 'Attack'),
('10','Hasty','Speed','Defense'),
('11','Impish','Defense','SpecialAttack'),
('12','Jolly','Speed','SpecialAttack'),
('13','Lax','Defense','SpecialDefense'),
('14','Lonely','Attack','Defense'),
('15','Mild','SpecialAttack','Defense'),
('16','Modest','SpecialAttack','Attack'),
('17','Naive','Speed','SpecialDefense'),
('18','Naughty','Attack','SpecialDefense'),
('19','Quiet','SpecialAttack','Speed'),
('20','Quirky', 'SpecialDefense', 'SpecialDefense'),
('21','Rash','SpecialAttack','SpecialDefense'),
('22','Relaxed','Defense','Speed'),
('23','Sassy','SpecialDefense','Speed'),
('24','Serious', 'Speed', 'Speed'),
('25','Tired','Speed','Attack');

INSERT INTO Trainers(TrainerID, `Name`)
VALUES
('1','Jacob');

INSERT INTO Pokemons(PokemonID, PokemonName, Gender, Description, NatureID, Level, BaseHealthPoints, BaseAttack, BaseDefense, BaseSpecialAttack, BaseSpecialDefense, BaseSpeed, HealthPointEVS,
AttackEVS, DefenseEVS, SpecialAttackEVS, SpecialDefenseEVS, SpeedEVS, HealthPointIVS, AttackIVS, DefenseIVS, SpecialAttackIVS, SpecialDefenseIVS, SpeedIVS, HealthPoints, Attack, Defense, SpecialAttack, SpecialDefense, Speed, TrainerID)
VALUES
('1','Snorlax','Male','Best Pokemon Ever', (11),'100','160','110','65','65','110','30','252','4','252','0','0','0','31','31','31','31','31','31','524','257','251','149','256','96', 1),
('2','Slaking','Female','Lazy', (1),'100','150','160','100','95','65','100',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, 1);

INSERT INTO Moves(MoveID, MoveName, Type, Category, Damage, Accuracy, PowerPoints)
VALUES
('1','Body Slam','Normal','Physical','85','100','15'),
('2',"Dragon's Ascent",'Dragon','Physical','120','100','5'),
('3','Precipice Blades','Ground','Physical','120','85','5'),
('4','Origin Pulse','Water','Special','110','85','5'),
('5','Explosion','Normal','Physical','250','100','5'),
('6','Self-Destruct','Normal','Physical','200','100','5'),
('7','Swords Dance','Normal','Status', null, '100', '20');

INSERT INTO PokemonsMoves(PokemonID, MoveID)
VALUES
('1','1'),
('1','2'),
('1','3'),
('1','4'),
('2','5');

INSERT INTO Items(ItemID, ItemName, Description, Quantity)
VALUES
('1','Full Restore','Heal selected Pokemon to full health and removes all status effects.','1');


INSERT INTO TrainersItems(TrainerID, ItemID)
VALUES
('1','1');
