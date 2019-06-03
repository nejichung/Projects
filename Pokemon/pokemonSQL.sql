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
Description varchar(50) NOT NULL,
Nature varchar(20) NOT NULL,
HealthPoints INT,
Attack INT,
Defense INT,
SpecialAttack INT, 
SpecialDefense INT,
Speed INT,
MoveID INT,
FOREIGN KEY (MoveID) REFERENCES Moves(MoveID));

CREATE TABLE Items (
ItemID INT PRIMARY KEY AUTO_INCREMENT,
ItemName varchar(20) NOT NULL,
Description varchar(50) NOT NULL);

