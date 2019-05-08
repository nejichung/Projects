CREATE DATABASE VendingMachine;

USE VendingMachine;

CREATE TABLE Items (
	ItemID int PRIMARY KEY,
    ItemName varchar(20),
    Quantity int,
    PriceInPennies int);
    
INSERT INTO Items (ItemID, ItemName, Quantity, PriceInPennies)
VALUES 
('1','Skittles','0','225'),
('2','Kit Kat','10','400'),
('3','Hot Cheetos','9','300'),
('4','Socks','976','1000');
	