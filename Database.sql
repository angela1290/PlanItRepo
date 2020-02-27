CREATE TABLE Budget(
ID int auto_increment primary key,
Income int,
Food_expenses int,
Rent_expenses int,
Savings int
);

CREATE TABLE User(
ID int auto_increment primary key,
Username varchar(100) UNIQUE NOT NULL,
Password varchar(100) NOT NULL,
Email varchar(100),
First_Name varchar(100),
Last_Name varchar(100),
Interest varchar(100),
Budget_ID int,
constraint FK_User_Budget foreign key (Budget_ID) references Budget(ID)
);

CREATE TABLE MyList(
ID int auto_increment primary key,
Title varchar(100),
Description varchar(200),
Deadline date
);

CREATE TABLE UserListConnection(
ID int auto_increment primary key,
MyList_ID int,
User_ID int, 
constraint FK_UserListConnection_MyList foreign key (MyList_ID) references MyList(ID),
constraint FK_UserListConnection_User foreign key (User_ID) references User(ID)
);

Select *
from user;

insert into user(username, password, email, First_name, Last_name, interest)
values('Angie', 'angie123', 'ang@gmail.com','Ang','ela','Sports'),
('Thal', 'thal123', 'thal@gmail.com','thal','ia','Horror'),
('KrisCross', 'kris123', 'kris@gmail.com','kris','ter','Technology'),
('Lennart', 'lenn123', 'lenn@gmail.com','lenn','art','Fashion');


