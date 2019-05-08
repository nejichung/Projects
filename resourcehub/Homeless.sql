DROP DATABASE IF EXISTS ResourceHub;
CREATE DATABASE ResourceHub;
USE ResourceHub;


CREATE TABLE Suppliers(
SupplierID int PRIMARY KEY AUTO_INCREMENT,
SupplierName varchar(25),
SupplierAddress varchar (50));

CREATE TABLE Requesters(
RequesterID int PRIMARY KEY AUTO_INCREMENT,
RequesterName varchar(25) NOT NULL);


CREATE TABLE Items(
ItemID int PRIMARY KEY auto_increment,
ItemName varchar(25),
Quantity int,
url varchar(50),
SupplierID int,
RequesterID int,
FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID),
FOREIGN KEY (RequesterID) REFERENCES Requesters(RequesterID));

CREATE TABLE Tags(
TagID int PRIMARY KEY auto_increment,
TagName varchar(25));

CREATE TABLE ItemsTags(
TagID int,
ItemID int,

FOREIGN KEY (TagID) REFERENCES Tags(TagID),
FOREIGN KEY (ItemID) REFERENCES Items(ItemID),
PRIMARY KEY(TagID, ItemID));

INSERT INTO Tags(TagID, TagName)
VALUES ('1','Furniture'),
('2','Food'),
('3','Electronics');

INSERT INTO Suppliers(SupplierID, SupplierName, SupplierAddress)
VALUES ('1','Target','Yolo swag ave, Minneapolis, MN'),
('2','The Software Guild','15 S 5th St #600, Minneapolis, MN 55402'),
('3','Best Buy','12234 3rd Street, Las Vegas, NV');

INSERT INTO Requesters(RequesterID, RequesterName)
VALUES ('1',"Mary's Place"),
('2','Goodwill'),
('3','Walmart');

INSERT INTO Items(ItemID, ItemName, Quantity, url, SupplierID, RequesterID)
VALUES('1','Couch', '10', null, null,'1'),
('2','Canned Beans', '1000', null, '2', null),
('3','Laptop', '5', null,'3',null);


INSERT INTO ItemsTags(TagID, ItemID)
VALUES ('1','1');

create table `user`(
`id` int primary key auto_increment,
`username` varchar(30) not null unique,
`password` varchar(100) not null,
`enabled` boolean not null,
SupplierID int,
RequesterID int,

FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID),
FOREIGN KEY (RequesterID) REFERENCES Requesters(RequesterID));

create table `role`(
`id` int primary key auto_increment,
`role` varchar(30) not null
);

create table `user_role`(
`user_id` int not null,
`role_id` int not null,
primary key(`user_id`,`role_id`),
foreign key (`user_id`) references `user`(`id`),
foreign key (`role_id`) references `role`(`id`));

insert into `user`(`id`,`username`,`password`,`enabled`)
    values(1,"admin", "password", true),
        (2,"supplier","password",true),
        (3,"requester","password",true);

insert into `role`(`id`,`role`)
    values(1,"ROLE_ADMIN"), (2,"ROLE_SUPPLIER"), (3,"ROLE_REQUESTER");
    
insert into `user_role`(`user_id`,`role_id`)
    values(1,1),(1,2),(1,3),(2,2),(3,3);
    
UPDATE user SET password = '$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS' WHERE id = 1;
UPDATE user SET password = '$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS' WHERE id = 2;
UPDATE user SET password = '$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS' WHERE id = 3;


DROP USER IF EXISTS 'jacob'@'localhost';
CREATE USER 'jacob'@'localhost' IDENTIFIED BY 'Rootroot123$';
GRANT ALL PRIVILEGES ON ResourceHub.* TO 'jacob'@'localhost';
FLUSH PRIVILEGES;

