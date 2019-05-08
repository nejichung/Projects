DROP DATABASE IF EXISTS GuessGameTest;
CREATE DATABASE GuessGameTest;

USE GuessGameTest;

CREATE TABLE Games(
GameKeyID int PRIMARY KEY AUTO_INCREMENT,
IsGameOver TINYINT(1),
TargetNumber int);

CREATE TABLE Rounds(
RoundID int PRIMARY KEY AUTO_INCREMENT,
GameKeyID int,
GuessNum int,
ExactCorrect int,
PartialCorrect int,
GuessResult varchar(10),
TimeOfGuess timestamp,

FOREIGN KEY (GameKeyID) REFERENCES Games(GameKeyID));

INSERT INTO Games( GameKeyID, IsGameOver, TargetNumber)
VALUES 
('1','0','1024');

INSERT INTO Rounds(RoundID, GameKeyID, GuessNum, ExactCorrect, PartialCorrect, GuessResult, TimeOfGuess)
VALUES
('1','1','2690','0','2','e: 0 p: 2','2019-03-10 13:48:12');
